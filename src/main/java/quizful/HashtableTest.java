package quizful;

import java.util.Hashtable;

public class HashtableTest {
    public static void main(String[] args) {
        Hashtable ht = new Hashtable();

        ht.put("1", "2");
        ht.put("2", "3");
        ht.put("3", "4");
        ht.put("4", "2");

        if(ht.contains("1")){
            System.out.print("1");
        }
        if(ht.contains("2")){
            System.out.print("2");
        }
        if(ht.contains("3")){
            System.out.print("3");
        }
        if(ht.contains("4")){
            System.out.print("4");
        }

    }
}
