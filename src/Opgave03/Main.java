package Opgave03;

import adjacencylistgraph.AdjacencyListGraph;
import adjacencylistgraph.Graph;

public class Main {
    public static void main(String[] args) {
        Graph<Integer> graph = new AdjacencyListGraph<>();

        graph.addVertex(15);
        graph.addVertex(6);
        graph.addVertex(38);
        graph.addVertex(66);
        graph.addVertex(123);

        graph.addEdge(15,38,10);
        graph.addEdge(15,6,23);
        graph.addEdge(15,66,90);
        graph.addEdge(38,123,55);
        graph.addEdge(38,66,2);
        graph.addEdge(123,6,7);
        graph.addEdge(123,66,76);
        graph.addEdge(66,6,8);

        graph.printGraph();

        System.out.println();
        System.out.println("Degree for 6:");
        System.out.println(graph.degree(6));
        System.out.println("Neighbors of 6:");
        System.out.println(graph.neighbors(6));
        System.out.println("Edges of 6:");
        System.out.println(graph.incidentEdges(6));

        System.out.println();
        System.out.println("Are 6 and 66 neighbors?");
        System.out.println(graph.areAdjacent(6,66));

        System.out.println("Are 6 and 38 neighbors?");
        System.out.println(graph.areAdjacent(6,38));

        System.out.println();
        System.out.println("Largest vertex:");
        System.out.println(largestVertex(graph));
    }

    public static int largestVertex(Graph<Integer> graph) {
        int largestVertex = Integer.MIN_VALUE;
        for (Integer vertex : graph.vertices()) {
            if (vertex > largestVertex) largestVertex = vertex;
        }
        return largestVertex;
    }
}
