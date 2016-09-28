package graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Calculate disctance from vertex 0 to every other vertices
 */

class VertexDistance {

    private static class UndiGraph {

        private int V; // vertex count
        private int E; // edges count
        private List<Integer>[] neighbours; // adjacency list of neighbours

        // graph creation using data from input stream
        public UndiGraph() {
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
        public UndiGraph(String fileName) {
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
            neighbours = (ArrayList<Integer>[])new ArrayList[V];
            for (int i = 0; i < V; i++) {
                neighbours[i] = new ArrayList<>();
            }
        }

        public int getV() {
            return V;
        }

        public int getE() {
            return E;
        }

        public List<Integer> getNeighbours(int vertex) {
            return neighbours[vertex];
        }

        private void addEdge(int v1, int v2) {
            if (v1 < 0 || v1 > V)
                throw new IllegalArgumentException(
                        "Broken number vertex " + v1);
            if (v2 < 0 || v2 > V)
                throw new IllegalArgumentException(
                        "Broken number vertex " + v2);
            neighbours[v1].add(v2);
            neighbours[v2].add(v1);
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();
            res.append("|V| = ").append(V).append("  |E| = ").append(E)
                    .append('\n').append("edges:\n");
            for (int v = 0; v < neighbours.length; v++) {
                res.append(v).append(":");
                for (Integer neighbour : neighbours[v]) {
                    res.append(' ').append(neighbour);
                }
                res.append('\n');
            }
            return res.toString();
        }
    }

    private static class Counter {

        private UndiGraph undiGraph;
        private int[] distance;
        private boolean seen[];
        Queue<Integer> queue;

        public Counter(UndiGraph undiGraph) {
            this.undiGraph = undiGraph;
            final int verticesCount = undiGraph.getV();
            seen = new boolean[verticesCount];
            distance = new int[verticesCount];
            queue = new LinkedList<>();
            queue.add(0);
            seen[0] = true;
            bfs();
        }

        private void bfs() {
            while (!queue.isEmpty()) {
                final Integer vertex = queue.poll();
                final int neighbourDist = distance[vertex] + 1;
                undiGraph.getNeighbours(vertex).stream()
                        .sequential()
                        .filter(neighbour -> !seen[neighbour])
                        .forEach(neighbour -> {
                            queue.add(neighbour);
                            distance[neighbour] = neighbourDist;
                            seen[neighbour] = true;
                        });
            }
        }

        public String getDistancesAsString() {
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < distance.length; i++) {
                res.append(distance[i]).append(' ');
            }
            return res.toString();
        }
    }

    public static void main(String[] args) {
        UndiGraph undiGraph = new UndiGraph(args[0]);
//        Graph undiGraph = new Graph();
        Counter counter = new Counter(undiGraph);
        System.out.println(counter.getDistancesAsString());
    }
}