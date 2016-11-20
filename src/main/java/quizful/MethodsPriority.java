package quizful;

public class MethodsPriority {

//    private static void var(Integer i1, int i2) {
//        System.out.println("Integer int");
//    }

    private static void var(int i1, int i2) {
        System.out.println("int int");
    }

//    private static void var(Integer i1, Integer i2) {
//        System.out.println("Integer Integer");
//    }

    private static void var(Integer... i) {
        System.out.println("Integer... i");
    }

    private static void var(int... i) {
        System.out.println("int... i");
    }

//    private static void var(Object... i) {
//        System.out.println("Object... i");
//    }

    public static void main(String[] args) {
        int i1 = 2;
        Integer i2 = 3;
        var(i1, i2);
    }
}
