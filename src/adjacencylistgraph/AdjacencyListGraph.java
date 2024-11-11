package adjacencylistgraph;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Adjacency list implementation of the graph interface.
 */
public class AdjacencyListGraph<V> implements Graph<V> {
    // List with all the vertices in the graph.
    private List<V> vertices;
    // Map with pairs containing (vertex, list of edges),
    // where list of edges is the incident edges to the vertex.
    // Note: Each edge is in 2 lists of incident edges.
    private Map<V, List<Edge<V>>> edges;

    //-----------------------------------------------------

    /** Construct an empty AdjacencyListGraph. */
    public AdjacencyListGraph() {
        vertices = new ArrayList<>();
        edges = new LinkedHashMap<>();
    }

    /** Return a list with the vertices in the graph. */
    @Override
    public List<V> vertices() {
        return new ArrayList<>(vertices);
    }

    /** Return a list with the edges in the graph. */
    @Override
    public List<Edge<V>> edges() {
        List<Edge<V>> tempEdges = new ArrayList<>();
        for (List<Edge<V>> edgeList : edges.values()) {
            for (Edge<V> edge : edgeList) {
                if (!tempEdges.contains(edge)) tempEdges.add(edge);
            }
        }
        return tempEdges;
    }

    /**
     * Return a list with the neighbors of the specified vertex.
     * Pre: The vertex is in the graph.
     */
    @Override
    public List<V> neighbors(V v) {
        assert vertices.contains(v);

        List<V> tempVertexes = new ArrayList<>();
        for (Edge<V> edge : edges.get(v)) {
            if (edge.getU().equals(v)) tempVertexes.add(edge.getV());
            else tempVertexes.add(edge.getU());
        }
        return tempVertexes;
    }

    /**
     * Return the degree for the specified vertex.
     * Pre: The vertex is in the graph.
     */
    @Override
    public int degree(V v) {
        assert vertices.contains(v);

        return edges.get(v).size();
    }

    /**
     * Return the incident edges to the specified vertex.
     * Pre: The vertex is in the graph.
     */
    @Override
    public List<Edge<V>> incidentEdges(V v) {
        assert vertices.contains(v);

        return edges.get(v);
    }

    /**
     * Return true, if the specified vertices are neighbors.
     * Pre: The vertices are vertices in the graph.
     */
    @Override
    public boolean areAdjacent(V u, V v) {
        assert vertices.contains(u);
        assert vertices.contains(v);

        return edges.get(u).contains(new Edge<>(u,v));
    }


    /** Print the vertices and the edges. */
    @Override
    public void printGraph() {
        for (V v : vertices) {
            List<Edge<V>> incidentEdges = edges.get(v);
            System.out.print("Vertex: " + v);
            System.out.println("\tIncident edges: " + incidentEdges);
        }
    }

    /**
     * Add a vertex to the graph.
     * Pre: The vertex is not in the graph before this addition.     */
    @Override
    public void addVertex(V v) {
        assert !vertices.contains(v);

        vertices.add(v);
        List<Edge<V>> list = new ArrayList<>();
        edges.put(v, list);
    }

    /**
     * Add an edge with the specified weight between the specified vertices to the graph.
     * Pre: Before addition, the vertices are in the graph, and the edge is not in the graph.
     * Pre: The weight is not negative.
     */
    @Override
    public Edge<V> addEdge(V u, V v, int weight) {
        assert vertices.contains(u);
        assert vertices.contains(v);
        assert !areAdjacent(u,v);
        assert weight >= 0;

        Edge<V> edge = new Edge<>(u, v, weight);
        edges.get(u).add(edge);
        edges.get(v).add(edge);
        return edge;
    }

    /**
     * Add an edge with weight 0 between the specified vertices to the graph.
     * Pre: Before addition, the vertices are in the graph, and the edge is not in the graph.
     */
    @Override
    public Edge<V> addEdge(V u, V v) {
        return addEdge(u,v,0);
    }


    /**
     * Remove the specified vertex from the graph.
     * Pre: The vertex is in the graph, and the vertex has no incident edges.
     */
    @Override
    public void removeVertex(V v) {
        assert vertices.contains(v);
        assert incidentEdges(v).isEmpty();

        vertices.remove(v);
    }

    /**
     * Remove the edge between the specified vertices from the graph.
     * Pre: The vertices are vertices in the graph,
     *   and The graph has an edge between the vertices.
     */
    @Override
    public void removeEdge(V u, V v) {
        assert vertices.contains(u);
        assert vertices.contains(v);
        assert areAdjacent(u,v);

        Edge<V> edgeToRemove = new Edge<>(u,v);
        edges.get(u).remove(edgeToRemove);
        edges.get(v).remove(edgeToRemove);
    }
}
