package misc;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapOntology {

    private static class SimpleLRUcache<K, V> extends LinkedHashMap<K, V> {

        private final int capacity;

        public SimpleLRUcache(int capacity) {
            super(capacity, 1.1f, true);
            this.capacity = capacity;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry eldest) {
            return this.size() > capacity;
        }
    }

    private static Map<Integer, String> storage = new SimpleLRUcache<>(2);

    public static void main(String[] args) {

        storage.put(1,"a");
        storage.put(2,"b");
        storage.put(3,"c");

        storage.get(2);

        storage.put(9,"h");

        System.out.println(storage);
    }

}
