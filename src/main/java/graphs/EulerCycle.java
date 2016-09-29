package graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Calculate euler's totalCycle
 */

class EulerCycle {

    // allows to delete edges
    private static class Graph {

        private static final int FIRST_INDEX = 1; // vertex numbering started with

        private int V; // vertex count
        private int E; // edges count
        private Map<Integer, Integer>[] adjacencies; // adjacency list of adjacencies
        // adjacencies[0] is not used

        // graph creation using data from input stream
        public Graph() {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(
                    System.in));) {
                parseHeader(br.readLine());
                for (int i = 0; i < getE(); i++) {
                    parseEdgeLine(br.readLine());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // graph creation using data from input file
        public Graph(String fileName) {
            final Path file = Paths.get(fileName);
            try (BufferedReader reader = Files.newBufferedReader(file)) {
                String line = reader.readLine();
                parseHeader(line);
                while ((line = reader.readLine()) != null) {
                    parseEdgeLine(line);
                }
            } catch (IOException e) {
                System.out.println("cant open file " + fileName);
                System.exit(1);
            }
        }

        private void parseEdgeLine(String line) {
            String[] pair;
            pair = line.split(" ");
            addEdge(Integer.parseInt(pair[0]),
                    Integer.parseInt(pair[1]));
        }

        private void parseHeader(String line) {
            String[] pair = line.split(" ");
            V = Integer.parseInt(pair[0]);
            E = Integer.parseInt(pair[1]);
            // vertex numbers starts with FIRST_INDEX
            adjacencies = (HashMap<Integer, Integer>[])new HashMap[V+FIRST_INDEX];
            for (int i = FIRST_INDEX; i < V+FIRST_INDEX; i++) {
                adjacencies[i] = new HashMap<>();
            }
        }

        public int getV() {
            return V;
        }

        public int getE() {
            return E;
        }

        public Map<Integer, Integer> getAdjacencies(int vertex) {
            return adjacencies[vertex];
        }

        private void addEdge(int v1, int v2) {
            addAdjacency(v1, v2);
            addAdjacency(v2, v1);
        }

        private void addAdjacency(int from, int to) {
            if (from < 0 || from > V)
                throw new IllegalArgumentException(
                        "Broken number vertex " + from);
            Integer edgesCount = adjacencies[from].get(to);
            if (edgesCount == null) {
                adjacencies[from].put(to,1);
            } else {
                adjacencies[from].put(to, edgesCount + 1);
            }
        }

        private void deleteEdge(int v1, int v2) {
            deleteAdjacency(v1, v2);
            deleteAdjacency(v2, v1);
            E--;
        }

        private void deleteAdjacency(int from, int to) {
            // TODO optimize: edgeCount is always must be the same for "trom" and "to"
            Integer edgesCount = adjacencies[from].get(to);
            if (edgesCount < 1) {
                throw new RuntimeException("can't delete nonexistent edge "
                        + from + " -> " + to);
            }
            edgesCount--;
            if (edgesCount > 0) {
                adjacencies[from].put(to, edgesCount);
            } else {
                adjacencies[from].remove(to);
            }
        }

        public Optional<Integer> pullAnyAdjacency(int vertex) {
            for (Map.Entry<Integer, Integer> adjacency : adjacencies[vertex].entrySet()) {
                final Integer count = adjacency.getValue();
                if (count > 0) {
                    final Integer toVertex = adjacency.getKey();
                    deleteEdge(vertex, toVertex);
                    return Optional.of(toVertex);
                }
            }
            return Optional.empty();
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();
            res.append("|V| = ").append(V).append("  |E| = ").append(E)
                    .append('\n').append("edges:\n");
            for (int v = FIRST_INDEX; v < adjacencies.length; v++) {
                res.append(v).append(":");
                for (Map.Entry<Integer, Integer> adjacency : adjacencies[v].entrySet()) {
                    res.append(' ').append(adjacency.getKey()).append(':')
                            .append(adjacency.getValue());
                }
                res.append('\n');
            }
            return res.toString();
        }

        public boolean hasEdges() {
            return E > 0;
        }
    }

    private static class Cycle {

        private final String NO_CYCLE = "NONE";

        private final int START_VERTEX = Graph.FIRST_INDEX;

        private List<Integer> totalCycle = new LinkedList<>();

        // vertices, which can extend totalCycle
        private TreeSet<Integer> candidates = new TreeSet<>();

        private final Graph graph;

        public Cycle(Graph graph) {
            this.graph = graph;
            candidates = new TreeSet<>();
        }

        public String getEulerCycle() {

            // check Euler's cycle existance
            int adjacenciesCount;
            for (int vertex = Graph.FIRST_INDEX; vertex < graph.getV() + Graph.FIRST_INDEX; vertex++) {
                adjacenciesCount = 0;
                for (Integer degree : graph.getAdjacencies(vertex).values()) {
                    adjacenciesCount += degree;
                }
                if (adjacenciesCount < 2 || (adjacenciesCount % 2) != 0) {
                    return NO_CYCLE;
                }
            }

            List<Integer> firstCycle = findCycle(START_VERTEX);
            totalCycle.addAll(firstCycle);

            while (!candidates.isEmpty()) {
                Integer candidate = candidates.pollFirst();
                List<Integer> addition = findCycle(candidate);
                if (addition.size() > 0) {
                    insertAddition(addition);
                }
                if (graph.getAdjacencies(candidate).size() != 0) {
                    candidates.add(candidate);
                }
            }

            if (graph.hasEdges()) {
                return NO_CYCLE;
            }

            return totalCycle.toString();
        }

        private void insertAddition(List<Integer> addition) {
            Integer first = addition.get(0);
            int indexFirst = totalCycle.indexOf(first);
            totalCycle.addAll(indexFirst, addition);
        }

        private List<Integer> findCycle(int start) {
            List<Integer> cycle = new LinkedList<>();
            int vertex = start;
            Optional<Integer> vertexOpt;
            vertexOpt = graph.pullAnyAdjacency(vertex);
            while (vertexOpt.isPresent()) {
                cycle.add(vertex);
                vertex = vertexOpt.get();
                candidates.add(vertex);
                vertexOpt = graph.pullAnyAdjacency(vertex);
            }
            return cycle;
        }
    }

    public static void main(String[] args) {
//        if (args.length < 1) {
//            throw new IllegalArgumentException("file name with " +
//                    "graph's description must be the first argument");
//        }
        Graph graph = new Graph(args[0]);
//        Graph graph = new Graph();
        Cycle cycle = new Cycle(graph);
        System.out.println(cycle.getEulerCycle().replaceAll("[,\\[\\]]", ""));
    }

}
