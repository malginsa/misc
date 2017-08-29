package lcpr.sc;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.xml.sax.helpers.DefaultHandler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class TableToXmlConverter extends DefaultHandler{

    private static final Logger LOG = LoggerContext.getContext().getLogger(LongTitleFilter.class.toString());

    private static String INPUT_FILE_NAME = "src/main/resources/SLPCT2HX";
    private static BufferedWriter bw = null;

    public static void main(String[] args) {

        Path inputPath = Paths.get(INPUT_FILE_NAME);
        Path outputPath = Paths.get(INPUT_FILE_NAME + ".xml");
        try {
            byte[] bytes = Files.readAllBytes(inputPath);
            BinaryText binaryText = new BinaryText(bytes);
            if (!binaryText.isText()) {
                LOG.error("It seems that input file is not a text file");
                return;
            }
            bw = Files.newBufferedWriter(outputPath, Charset.defaultCharset());
            bw.write("<?xml version=\"1.1\" encoding=\"ISO-8859-1\"?>\n");
            bw.write("<root>\n");
            byte[] line;
            boolean inDescription = false;
            while(null != (line = binaryText.nextLine())) {
                if (line.length < 1) {
                    continue;
                }
                if ((line[0] == '*')) {
                    if (!inDescription) {
                        bw.write("<description>\n");
                    }
                    inDescription = true;
                    bw.write("\t" + new String(line) + "\n");
                    continue;
                }
                if (inDescription) {
                    inDescription = false;
                    bw.write("</description>\n");
                }
                parseRule(line);
            }
            bw.write("</root>\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void parseRule(byte[] line) {
        if ((null == line) || (line.length == 0) || (line[0] != '%') || line.length < 3) {
            LOG.error("error occurred while parsing line: " + new String(line));
            return;
        }
        String percent = new String(Arrays.copyOfRange(line, 1, 3));
        if (line.length < 9) {
            writeRule("%" + percent, "");
            return;
        }
        try {
            Hex.decodeHex(percent.toCharArray());
        } catch (DecoderException e) {
            LOG.error("incorrect input translation rule: " + line);
        }
        byte[] value = Arrays.copyOfRange(line, 8, line.length);
        if (((char)line[8] == '%') && line.length == 11) { // the value is the percent code
            String valueAsString = new String(value);
            writeRule("%" + percent, valueAsString);
            return;
        }
        String encoded = encodeAllChars(value);
        writeRule("%" + percent, encoded);
        //

// option with escapeXML
//        if ((line.length == 9) || (line.length == 10)) {
//            String valueAsString = new String(value, StandardCharsets.ISO_8859_1);
//            String escaped = StringEscapeUtils.escapeXml11(valueAsString);
//            switch (escaped) {
//                case "\t": writeRule("%" + percent, "&#9;");
//                    break;
//                case "\r": writeRule("%" + percent, "&#13;");
//                    break;
//                default:
//                    writeRule("%" + percent, escaped);
//            }
//        }
    }

    private static String encodeAllChars(byte[] value) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < value.length; i++) {
            int asInt = Byte.toUnsignedInt(value[i]);
            result.append("&#")
                    .append(asInt)
                    .append(';');
        }
        return result.toString();
    }

    private static void writeRule(String from, String to) {
        try {
            bw.write("\t<rule>");
                bw.write("\t\t<from>");
                    bw.write(from);
                bw.write("</from>");
                bw.write("\t\t<to>");
                    bw.write(to);
                bw.write("</to>");
            bw.write("\t</rule>\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
