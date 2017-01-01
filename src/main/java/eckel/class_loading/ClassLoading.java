package eckel.class_loading;

public class ClassLoading {
    public static void main(String[] args) {

        new Candy(); // as a side effect class will be loaded

        // parent will be loaded as well
        try {
            Class.forName("ChocoCandy");
        } catch (ClassNotFoundException e) {
            System.out.println("Couldn't find ChocoCandy");
        }
    }
}
