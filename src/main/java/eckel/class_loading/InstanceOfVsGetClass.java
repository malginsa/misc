package eckel.class_loading;

import java.io.Serializable;

public class InstanceOfVsGetClass {

    public static void checkInstance(Object object) {
        if( object instanceof Candy ) {
            System.out.println("instance of Candy");
        }
        if( object instanceof ChocoCandy ) {
            System.out.println("instance of ChocoCandy");
        }
        if( object instanceof Serializable) {
            System.out.println("instance of Serializable");
        }
        if( object instanceof Comparable) {
            System.out.println("instance of Comparable");
        }
    }

    public static void main(String[] args) {

        Candy candy = new Candy();
        ChocoCandy chocoCandy = new ChocoCandy();

        Class<? extends Candy> candyClass = candy.getClass();
        System.out.println(candyClass.getName());

        Class<? extends ChocoCandy> chocoCandyClass = chocoCandy.getClass();
        System.out.println(chocoCandyClass.getName());

        System.out.println("checking candy:");
        checkInstance(candy);

        System.out.println("checking chocoCandy:");
        checkInstance(chocoCandy);

        String.class.getName();
    }
}
