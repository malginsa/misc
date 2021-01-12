package misc;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AnnotationCollector {

    private static final Pattern ANNOTATION = Pattern.compile("@[A-Z]");

    public static void main(String[] args) {
        String rootDirectoryName = new Scanner(System.in).nextLine();
        Iterable<String> annotations = new AnnotationCollector().collect(rootDirectoryName);
        annotations.forEach(System.out::println);
    }

    private static Stream<? extends String> getStreamOfLines(File file) {
        try {
            return Files.lines(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return Stream.empty();
        }
    }

    private Iterable<String> collect(String rootDirectoryName) {
        return FileUtils.listFiles(new File(rootDirectoryName), new String[]{"java"}, true)
                .stream()
                .flatMap(AnnotationCollector::getStreamOfLines)
                .filter(s -> ANNOTATION.matcher(s).find())
                .map(String::trim)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

}
