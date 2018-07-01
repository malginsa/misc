package misc;

public class AmendmentContains
{
    private static final String STOCKNOTE = "<stocknote>\n" +
            "\t<annotations>\n" +
            "\t\t<hist.note.block>\n" +
            "\t\t\t<codes.head ID=\"undefined\">\n" +
            "\t\t\t\t<metadata.block owner=\"undefined\">\n" +
            "\t\t\t\t\t<md.mnem>xhsuw</md.mnem>\n" +
            "\t\t\t\t\t<md.pub.tag.info>\n" +
            "\t\t\t\t\t\t<md.pub.tag>NOPUB</md.pub.tag>\n" +
            "\t\t\t\t\t</md.pub.tag.info>\n" +
            "\t\t\t\t\t<md.source.tag>MV-02</md.source.tag>\n" +
            "\t\t\t\t</metadata.block>\n" +
            "\t\t\t\t<head.info>\n" +
            "\t\t\t\t\t<headtext>&emsp;</headtext>\n" +
            "\t\t\t\t</head.info>\n" +
            "\t\t\t</codes.head>\n" +
            "\t\t\t<hist.note.body>\n" +
            "\t\t\t\t<codes.head ID=\"undefined\">\n" +
            "\t\t\t\t\t<metadata.block owner=\"undefined\">\n" +
            "\t\t\t\t\t\t<md.mnem>shfn</md.mnem>\n" +
            "\t\t\t\t\t\t<md.pub.tag.info>\n" +
            "\t\t\t\t\t\t\t<md.pub.tag>NOPUB</md.pub.tag>\n" +
            "\t\t\t\t\t\t</md.pub.tag.info>\n" +
            "\t\t\t\t\t\t<md.source.tag>MV-02</md.source.tag>\n" +
            "\t\t\t\t\t</metadata.block>\n" +
            "\t\t\t\t\t<head.info>\n" +
            "\t\t\t\t\t\t<headtext>Amendments</headtext>\n" +
            "\t\t\t\t\t</head.info>\n" +
            "\t\t\t\t</codes.head>\n" +
            "\t\t\t\t<hist.note.body>\n" +
            "\t\t\t\t\t<codes.head ID=\"undefined\">\n" +
            "\t\t\t\t\t\t<metadata.block owner=\"undefined\">\n" +
            "\t\t\t\t\t\t\t<md.mnem>ssn1</md.mnem>\n" +
            "\t\t\t\t\t\t\t<md.pub.tag.info>\n" +
            "\t\t\t\t\t\t\t\t<md.pub.tag>NOPUB</md.pub.tag>\n" +
            "\t\t\t\t\t\t\t</md.pub.tag.info>\n" +
            "\t\t\t\t\t\t\t<md.source.tag>MV-02</md.source.tag>\n" +
            "\t\t\t\t\t\t</metadata.block>\n" +
            "\t\t\t\t\t\t<head.info>\n" +
            "\t\t\t\t\t\t\t<headtext>2018 Amendments</headtext>\n" +
            "\t\t\t\t\t\t</head.info>\n" +
            "\t\t\t\t\t</codes.head>\n" +
            "\t\t\t\t\t<hist.note type=\"Amendment\">\n" +
            "\t\t\t\t\t\t<para ID=\"undefined\">\n" +
            "\t\t\t\t\t\t\t<metadata.block owner=\"undefined\">\n" +
            "\t\t\t\t\t\t\t\t<md.mnem>gnp</md.mnem>\n" +
            "\t\t\t\t\t\t\t\t<md.pub.tag.info>\n" +
            "\t\t\t\t\t\t\t\t\t<md.pub.tag>NOPUB</md.pub.tag>\n" +
            "\t\t\t\t\t\t\t\t</md.pub.tag.info>\n" +
            "\t\t\t\t\t\t\t\t<md.source.tag>MV-02</md.source.tag>\n" +
            "\t\t\t\t\t\t\t</metadata.block>\n" +
            "\t\t\t\t\t\t\t<paratext>\n" +
            "\t\t\t\t\t\t\t\tSubsec. (\n" +
            "\t\t\t\t\t\t\t\t<parameter name=\"targetLocationSubsection\" />\n" +
            "\t\t\t\t\t\t\t\t). &ensp;Pub.L. 111&ndash;\n" +
            "\t\t\t\t\t\t\t\t<parameter name=\"classNumber\" />\n" +
            "\t\t\t\t\t\t\t\t, &sect;&ensp;\n" +
            "\t\t\t\t\t\t\t\t<parameter name=\"sectionNumber\" />\n" +
            "\t\t\t\t\t\t\t\t, added \n" +
            "\t\t\t\t\t\t\t\t<parameter name=\"paragraphType\" />\n" +
            "\t\t\t\t\t\t\t\t (\n" +
            "\t\t\t\t\t\t\t\t<parameter name=\"targetLocationSubsectionAdd\" />\n" +
            "\t\t\t\t\t\t\t\t).\n" +
            "\t\t\t\t\t\t\t</paratext>\n" +
            "\t\t\t\t\t\t</para>\n" +
            "\t\t\t\t\t</hist.note>\n" +
            "\t\t\t\t</hist.note.body>\n" +
            "\t\t\t</hist.note.body>\n" +
            "\t\t</hist.note.block>\n" +
            "\t</annotations>\n" +
            "</stocknote>\n";

    public static final String AMENDMENT_MARKER = "<headtext>Amendments</headtext>";

    public static void main(String[] args)
    {
        boolean isAmendment = STOCKNOTE.contains(AMENDMENT_MARKER);
        System.out.println(isAmendment);
    }
}
