package misc.memory_model;
public class case1 {
    public static void main(String[] args) {
        int i = 7;
        method(i);
        System.out.println("i=" + i);
    }
    private static void method(int i) {
        i++;
        System.out.println("i=" + i);
    }
}
