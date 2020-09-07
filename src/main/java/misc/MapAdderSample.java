package misc;

import org.apache.commons.collections.MapUtils;

import java.util.HashMap;
import java.util.Map;

public class MapAdderSample
{
    Map<Integer, String> map = new HashMap<>();

    private void addToMap(Integer key, String value)
    {
        map.computeIfAbsent(key, k -> "number "+k);
    }

    public static void main(String[] args)
    {
        MapAdderSample mapAdderSample = new MapAdderSample();
        mapAdderSample.addToMap(1, "one");
        mapAdderSample.addToMap(1, "One");
        mapAdderSample.addToMap(2, "Two");
        MapUtils.debugPrint(System.out, "myMap", mapAdderSample.map);
    }
}
