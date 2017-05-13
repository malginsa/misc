package tkach;

import java.util.Map;
import java.util.WeakHashMap;

public class WeakHashMapSample {

    private static class Data {}

    public static void main(String[] args) {

        Map<Data, String> map = new WeakHashMap<>();
        Data data = new Data();
        map.put(data, "information");

        data = null;
        System.gc();

        for (int i = 1; i < 1_000_000; i++) {
            if (map.isEmpty()) {
                System.out.println("Empty! i=" + i);
                break;
            }
        }
    }
}
