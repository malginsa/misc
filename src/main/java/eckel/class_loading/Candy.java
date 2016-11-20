package eckel.class_loading;

import java.io.Serializable;

public class Candy implements Serializable{
    static {
        System.out.println("загрузка класса Candy");
    }
}
