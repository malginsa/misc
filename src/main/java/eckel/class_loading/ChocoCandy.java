package eckel.class_loading;

public class ChocoCandy extends Candy {
    static {
        System.out.println("загрузка класса ChokoCandy");
    }
}
