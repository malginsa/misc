package quizful;

public class MiscCompare {

    public String name;

    public MiscCompare(String name) {
        this.name = name;
    }

    private static void nameCompare(String s1, String s2) {
        MiscCompare h1 = new MiscCompare(s1);
        MiscCompare h2 = new MiscCompare(s2);
        System.out.println(h1.name == h2.name);
    }

    public static void main(String[] args) {

        nameCompare("String", "String"); // true
        nameCompare("String", "Str" + "ing"); // true
        nameCompare(new String("String"), new String("String")); // false
        nameCompare(new String("String").intern(), new String("String").intern()); // true

        System.out.println("String" == "String"); // true
        System.out.println("String" == "Str" + "ing"); // true

        String s1 = "Str";
        String s2 = "ing";
        String s3 = "String";
        String s4 = "String";
        System.out.println("String" == s1 + s2); // false
        System.out.println("String" == s3); // true
        System.out.println(s4 == s3); // true
    }

}