package graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Connected component count in undirected graph
 * vertex numbers starts with '1'
 */

class CCCounter {

    private static class UndiGraph {

        private int V; // vertex count
        private int E; // edges count
        private List<Integer>[] neighbours; // adjacency list of neighbours
        // neighbours[0] ist not used

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
            // vertex numbers starts with '1'
            neighbours = (ArrayList<Integer>[])new ArrayList[V+1];
            for (int i = 1; i < V+1; i++) {
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
            for (int v = 1; v < neighbours.length; v++) {
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

        private int count;
        private UndiGraph undiGraph;
        private boolean visited[];

        public Counter(UndiGraph undiGraph) {
            this.undiGraph = undiGraph;
            visited = new boolean[undiGraph.getV() + 1];
            for (int v = 1; v < undiGraph.getV() + 1; v++) {
                if (!visited[v]) {
                    count++;
                    dfs(v);
                }
            }
        }

        private void dfs(int vertex) {
            visited[vertex] = true;
            List<Integer> neighbours = undiGraph.getNeighbours(vertex);
            neighbours.stream()
                    .sequential()
                    .filter(neighbour -> !visited[neighbour])
                    .forEach(this::dfs);
        }

        public int getCount() {
            return count;
        };
    }

    public static void main(String[] args) {

//        if (args.length < 1) {
//            throw new IllegalArgumentException("file name with " +
//                    "graph's description must be the first argument");
//        }
//
//        Graph undiGraph = new Graph(args[0]);
        UndiGraph undiGraph = new UndiGraph();
        Counter connCompCounter = new Counter(undiGraph);
        System.out.println(connCompCounter.getCount());

    }
}