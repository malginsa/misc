package misc.memory_model;
public class case2 {
    public static void main(String[] args) {
        int[] arr = new int[] {3,6};
        changer(arr);
        System.out.println(arr[0]);
    }
    private static void changer(int[] arr) {
        arr[0] = 7;
        System.out.println(arr[0]);
    }
}
