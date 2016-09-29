package misc;

import java.util.HashMap;
import java.util.Map;

public class HashMapSize {

    public static void main(String[] args) {

        int COUNT = 1_000_000;

        long free1 = Runtime.getRuntime().freeMemory();
        long used1 = Runtime.getRuntime().totalMemory() - free1;

        Map<Integer, Integer>[] arr = (Map<Integer, Integer>[]) new HashMap[COUNT];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = new HashMap<>();
        }

        final Integer value = arr[1].get(2);
        System.out.println(value);

        long free2 = Runtime.getRuntime().freeMemory();
        long used2 = Runtime.getRuntime().totalMemory() - free2;

        System.out.println("free memory consumption: " + (free1 - free2));
        System.out.println("used memory difference: " + (used2 - used1));


        Map<Integer, Integer>[] adj =
                (HashMap<Integer, Integer>[])new HashMap[10];

        adj[0] = new HashMap<>();
        adj[0].put(1,1);
        adj[0].put(2,2);
        System.out.println(adj[0]);
        adj[0].remove(1);
        System.out.println(adj[0]);
    }
}
