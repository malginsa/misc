package challenge;

import java.util.Arrays;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * challenge: given array of Strings.
 * to do: to calculate unique chars, which are consisted in each String
 */
public class RockCollection {

    /**
     * Dennis's version
     */
    public static long gemstones1(String[] rocks) {
        String shortest = rocks[0];
        long res = 0;
        for (String rock : rocks) {
            if (rock.length() < shortest.length()) {
                shortest = rock;
            }
        }
        Set<Character> set = shortest.chars().mapToObj(c -> (char) c).collect(Collectors.toSet());
        for (Character character : set) {
            int count = 0;
            for (String rock : rocks) {
                if (rock.indexOf(character) >= 0) {
                    count++;
                }
            }
            if (count == rocks.length) {
                res++;
            }
        }
        return res;
    }

    /**
     * Streamed version
     */
    public static long gemstones2(String[] rocks) {
        return Arrays.stream(rocks)
                .map(String::chars)
                .map(strm -> strm.mapToObj(c -> (char)c).collect(Collectors.toSet()))
                .flatMap(set -> set.stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(el -> ( el.getValue() == rocks.length ))
                .count();
    }
}
