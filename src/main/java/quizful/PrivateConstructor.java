package quizful;

class Alpha {
    static String s = "";
//    private Alpha() { s += "alpha "; } // compilation error
    protected Alpha() { s += "alpha "; }
}

class SubAlpha extends Alpha {
    private SubAlpha() { s += "subsub "; }
    public static void main(String[] args) {
        new SubAlpha();
        System.out.println(s);
    }
}

