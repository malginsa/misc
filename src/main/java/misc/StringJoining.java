package misc;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.joining;

public class StringJoining {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Bob", "Alice", "Tim");
        System.out.println(
                names.stream()
                        .map(String::toUpperCase)
                        .collect(joining(", ")));
    }
}
