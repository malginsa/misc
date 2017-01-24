package misc;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static java.nio.file.FileVisitResult.*;

public class FileDupesFinder {

    private static class FileSystemCrawler extends SimpleFileVisitor<Path> {

        private Map<Long, List<Path>> uniques;

        public FileSystemCrawler() {
            uniques = new HashMap<>();
        }

        public Map<Long, List<Path>> getUniques() {
            return uniques;
        }

        // Print information about each type of file.
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
            if (!attr.isRegularFile()) {
                return CONTINUE;
            }
            long size = attr.size();
//            System.out.println("CRAWLING FILE: " + file + " (" + size + " bytes)");
            if (uniques.containsKey(size)) {
                // add to an existing list
                uniques.get(size).add(file);
//                System.out.println("DUPE FOUND FOR SIZE = " + size);
//                System.out.println("NAME ADDED: " + file);
            } else {
                // new list
                LinkedList<Path> newList = new LinkedList<>();
                newList.add(file);
                uniques.put(size, newList);
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

        if (args.length < 1) {
            System.out.println("usage: misc.FileDupesFinder \"root-dir\"");
            System.exit(1);
        }

        FileDupesFinder fileDupesFinder = new FileDupesFinder();
        FileSystemCrawler crawler = new FileSystemCrawler();
        Files.walkFileTree(Paths.get(args[0]), crawler);
        Map<Long, List<Path>> uniques = crawler.getUniques();

        long totalSize = 0;
        long totalDupes = 0;
        System.out.println("\n --- DUPES ---");
        for (Map.Entry<Long, List<Path>> entry : uniques.entrySet()) {
            List<Path> dupesList = entry.getValue();
            if (dupesList.size() < 2) {
                continue;
            }
            Long size = entry.getKey();
            totalDupes += (dupesList.size()-1);
            totalSize += size * (dupesList.size()-1);
            System.out.println(size);
            dupesList.stream().forEach(System.out::println);
        }
        System.out.println(" --- TOTAL ---");
        System.out.println("FILE DUPES = " + totalDupes);
        System.out.println("SIZE = " + totalSize/1024/1024/1024 + " GB");

    }

}
