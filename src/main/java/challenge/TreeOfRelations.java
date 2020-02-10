package challenge;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class TreeOfRelations
{
    public static void main(String[] args) throws IncorrectInputListOfRelations
    {
        List<Relation> input = new ArrayList<>();
        input.add(new Relation("animal", "mammal"));
        input.add(new Relation("animal", "bird"));
        input.add(new Relation("lifeform", "animal"));
        input.add(new Relation("cat", "lion"));
        input.add(new Relation("mammal", "cat"));
        input.add(new Relation("animal", "fish"));
        printTreeUsingAdjacencyList(input);
        printTreeUsingQueue(input);
        printTreeUsingTreeOfNodes(input);
    }

    private static void printTreeUsingAdjacencyList(List<Relation> listOfRelations) throws IncorrectInputListOfRelations
    {
        System.out.println("--- Using adjacency list ---");
        Map<String, List<String>> adjacencyList = new HashMap<>();
        for (Relation relation : listOfRelations)
        {
            List<String> childrenOfParent = adjacencyList.computeIfAbsent(relation.parent, foo -> new ArrayList<>());
            childrenOfParent.add(relation.child);
        }
        dfs(findNameOfRoot(listOfRelations), 0, adjacencyList);
    }

    private static void dfs(final String nameOfNode, final int depth, final Map<String, List<String>> adjacencyList)
    {
        System.out.println(calculateIndentation(depth) + nameOfNode);
        if (adjacencyList.get(nameOfNode) != null)
            for (String child : adjacencyList.get(nameOfNode))
            {
                dfs(child, depth+1, adjacencyList);
            }
    }

    private static void printTreeUsingQueue(List<Relation> listOfRelations) throws IncorrectInputListOfRelations
    {
        System.out.println("--- Using queue ---");
        Map<String, List<Relation>> adjacencyList = listOfRelations.stream()
                .collect(groupingBy(Relation::getParent));
        String nameOfRoot = findNameOfRoot(listOfRelations);
        Deque<String> deque = new LinkedList<>();
        deque.add(nameOfRoot);
        while (!deque.isEmpty())
        {
            String currentNode = deque.pop();
            System.out.println(currentNode);
            List<Relation> childOfCurrentNode = adjacencyList.get(currentNode);
            if (childOfCurrentNode != null)
            {
                childOfCurrentNode
                        .stream()
                        .sorted(Collections.reverseOrder())
                        .map(Relation::getChild)
                        .forEach(deque::addFirst);
            }
        }
    }

    private static void printTreeUsingTreeOfNodes(final List<Relation> listOfRelations) throws IncorrectInputListOfRelations
    {
        System.out.println("--- Using tree of nodes ---");
        List<Node> toProcess = new LinkedList<>();
        Node root = findRoot(listOfRelations);
        toProcess.add(root);
        while (!toProcess.isEmpty())
        {
            Node node = toProcess.remove(0);
            List<Node> sortedChilderOfParent = getSortedChilderOfParent(node.name, listOfRelations);
            node.children.addAll(sortedChilderOfParent);
            toProcess.addAll(sortedChilderOfParent);
        }
        root.print(" ");
    }

    private static List<Node> getSortedChilderOfParent(final String nameOfParent, final List<Relation> listOfRelations)
    {
        return listOfRelations.stream()
                .filter((relation) -> relation.parent.equals(nameOfParent))
                .sorted()
                .map(Relation::getChild)
                .map(Node::new)
                .collect(Collectors.toList());
    }

    private static class Node
    {
        String name;
        List<Node> children;

        Node(final String name)
        {
            this.name = name;
            children = new ArrayList<>();
        }

        public Node(final String name, final Node child)
        {
            this(name);
            this.children.add(child);
        }

        public void print(final String indent)
        {
            System.out.println(indent + name);
            for (Node child : children)
            {
                child.print(indent + "   ");
            }
        }
    }

    private static class Relation implements Comparable<Relation>
    {
        String parent;
        String child;
        private static int orderCounter;
        int order;
        Relation(final String parent, final String child)
        {
            this.parent = parent;
            this.child = child;
            order = orderCounter++;
        }

        @Override
        public String toString()
        {
            return "Relation{" +
                    "parent='" + parent + '\'' +
                    ", child='" + child + '\'' +
                    '}';
        }

        @Override
        public int compareTo(@NotNull final Relation other)
        {
            return this.order - other.order;
        }

        String getChild()
        {
            return child;
        }

        String getParent()
        {
            return parent;
        }
    }

    @NotNull
    private static String calculateIndentation(final int depth)
    {
        return new String(new char[depth]).replace("\0", "   ");
    }

    private static String findNameOfRoot(final List<Relation> listOfRelations) throws IncorrectInputListOfRelations
    {
        return findRoot(listOfRelations).name;
    }

    private static Node findRoot(final List<Relation> listOfRelations) throws IncorrectInputListOfRelations
    {
        Set<String> parents = new HashSet<>();
        Set<String> children = new HashSet<>();
        for (Relation relation : listOfRelations)
        {
            parents.add(relation.parent);
            children.add(relation.child);
        }
        parents.removeAll(children);
        if (parents.size() != 1) throw new IncorrectInputListOfRelations();
        return new Node(parents.iterator().next());
    }

    private static class IncorrectInputListOfRelations extends Throwable
    {
    }
}
