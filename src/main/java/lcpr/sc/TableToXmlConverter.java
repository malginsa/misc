package lcpr.sc;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import org.xml.sax.helpers.DefaultHandler;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TableToXmlConverter extends DefaultHandler{

    private static String INPUT_FILE_NAME = "src\\main\\resources\\SLPCT2HX";
    private static BufferedWriter bw = null;

    public static void main(String[] args) {

        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader br = null;

        Path output = Paths.get(INPUT_FILE_NAME + ".xml");

        try {
            inputStream = new FileInputStream(new File(INPUT_FILE_NAME)) {};
            inputStreamReader = new InputStreamReader(inputStream);
            br = new BufferedReader(inputStreamReader);
            bw = Files.newBufferedWriter(output, Charset.defaultCharset());
            bw.write("<?xml version=\"1.0\"?>\n");
            bw.write("<root>\n");
            bw.write("<description>\n");
            bw.write("\tThis table is used by the WFPCTRANS program to translate percent\n" +
                    "\tlanguage contained in vendor-keyed data.\n" +
                    "\t!!! PLEASE NOTE THIS TABLE IS USED FOR SESSION LAW DATA ONLY !!!\n" +
                    "\tAll 256 possible %'s must be listed (from %00 to %FF)\n" +
                    "\tBegin % code in column 1 and the new string in column 9\n" +
                    "\tThis table was copied from WLAWDB.PROD@B.SYSIN(PCT2HEXT).\n");
            bw.write("</description>\n");
            String line;
            while(null != (line = br.readLine())) {
                parseLine(line);
            }
            bw.write("</root>\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
                br.close();
                inputStreamReader.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private static void parseLine(String line) {

        if ((null == line) || line.isEmpty() || (line.charAt(0) != '%') || line.length() < 9) {
            return;
        }
        String percentAsString = line.substring(1, 3);
        Integer percent = Integer.decode("0x" + percentAsString);
        String value = line.substring(8, line.length());
        if ((percent >= 0) && (percent <= 255) && (value.length() > 0)) {
            writeRule("%" + percentAsString, value);
        } else {
            System.err.println("error occurred while parsing line: " + line);
        }
    }

    private static void writeRule(String from, String to) {
        try {
            bw.write("\t<rule>\n");
                bw.write("\t\t<from>");
                    bw.write(from);
                bw.write("</from>\n");
                bw.write("\t\t<to>");
                    bw.write(to);
                bw.write("</to>\n");
            bw.write("\t</rule>\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
