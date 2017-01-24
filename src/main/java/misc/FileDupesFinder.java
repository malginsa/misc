package misc;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

import static java.nio.file.FileVisitResult.*;

public class FileDupesFinder {

    private static class FileSystemCrawler extends SimpleFileVisitor<Path> {

        private Map<Long, Path> uniques;

        public FileSystemCrawler() {
            uniques = new HashMap<>();
        }

        // Print information about each type of file.
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
            if (!attr.isRegularFile()) {
                return CONTINUE;
            }
            long size = attr.size();
            System.out.println("CRAWLING FILE: " + file + " (" + size + " bytes)");
            if (uniques.containsKey(size)) {
                System.out.println("DUPE FOUND FOR SIZE = " + size);
                System.out.println("NAME 1: " + uniques.get(size));
                System.out.println("NAME 2: " + file);
            } else {
                uniques.put(size, file);
            }
            return CONTINUE;
        }

        // Print each directory visited.
        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
            return CONTINUE;
        }

        // If there is some error accessing the file, let the user know.
        // If you don't override this method and an error occurs, an IOException is thrown.
        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) {
            System.err.println(exc);
            return CONTINUE;
        }
    }

    public static void main(String[] args) throws IOException {
        FileDupesFinder fileDupesFinder = new FileDupesFinder();
        Files.walkFileTree(Paths.get("/home/msa/Downloads"), new FileSystemCrawler());
    }

}
