package domain;

import java.util.*;

/**
 * The Grafo class represents an undirected weighted graph.
 * It provides methods for adding nodes and edges, checking connectivity between
 * nodes,
 * and finding the shortest path between two nodes using Dijkstra's algorithm.
 *
 * This class implements the Serializable interface, allowing instances to be
 * serialized and deserialized.
 *
 * @author Daniel Diaz and Miguel Motta
 * @version 1.0
 * @since 2024-05-25
 */

public class Grafo {
    private int numNodes;
    private int[][] matrix;
    private HashMap<Integer, Object> nodes;

    /**
     * Constructs a new Grafo instance with the specified number of nodes.
     *
     * @param numNodes the number of nodes in the graph
     */
    public Grafo(int numNodes) {
        this.numNodes = numNodes;
        this.matrix = new int[numNodes][numNodes];
        this.nodes = new HashMap<>();
    }

    /**
     * Adds a new node to the graph with the specified id and data.
     *
     * @param id   the unique identifier of the node
     * @param dato the data associated with the node
     */
    public void addNode(int id, Object dato) {
        nodes.put(id, dato);
    }

    /**
     * Adds an edge between two nodes with the specified weight.
     *
     * @param nodoA the id of the first node
     * @param nodoB the id of the second node
     * @param peso  the weight of the edge
     */
    public void addVertex(int nodoA, int nodoB, int peso) {
        matrix[nodoA][nodoB] = peso;
        matrix[nodoB][nodoA] = peso;
    }

    /**
     * Checks if there is a path between two nodes in the graph.
     *
     * @param nodoA the id of the first node
     * @param nodoB the id of the second node
     * @return true if there is a path between the nodes, false otherwise
     */
    public boolean isConnected(int nodoA, int nodoB) {
        boolean[] visited = new boolean[numNodes];
        dfs(nodoA, visited);

        return visited[nodoB];
    }

    /**
     * Finds the shortest path between two nodes in the graph using Dijkstra's
     * algorithm.
     *
     * @param nodoA the id of the starting node
     * @param nodoB the id of the destination node
     * @return a list of node ids representing the shortest path
     * @throws QuoriPOOBException if there is no path between the nodes
     */
    public List<Integer> shortestWay(int nodoA, int nodoB) throws QuoriPOOBException {
        if (!isConnected(nodoA, nodoB)) {
            QuoriPOOBException e=  new QuoriPOOBException(QuoriPOOBException.IMPPOSSIBLE_TO_REACH);
            Log.record(e);
				throw e;
        }

        int[] distances = new int[numNodes];
        boolean[] visited = new boolean[numNodes];
        int[] previos = new int[numNodes];
        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(previos, -1);
        distances[nodoA] = 0;

        for (int i = 0; i < numNodes; i++) {
            int nodeMin = minDistancia(distances, visited);

            if (nodeMin == -1) {
                break; // Todos los nodos han sido visitados
            }

            visited[nodeMin] = true;

            for (int j = 0; j < numNodes; j++) {
                if (!visited[j] && matrix[nodeMin][j] != 0 && distances[nodeMin] != Integer.MAX_VALUE
                        && distances[nodeMin] + matrix[nodeMin][j] < distances[j]) {
                    distances[j] = distances[nodeMin] + matrix[nodeMin][j];
                    previos[j] = nodeMin;
                }
            }
        }

        List<Integer> way = new ArrayList<>();
        int currentNode = nodoB;

        while (currentNode != -1) {
            way.add(currentNode);
            currentNode = previos[currentNode];
        }

        Collections.reverse(way);
        return way;
    }

    private int minDistancia(int[] distances, boolean[] visited) {
        int minVal = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < numNodes; i++) {
            if (!visited[i] && distances[i] <= minVal) {
                minVal = distances[i];
                minIndex = i;
            }
        }

        return minIndex;
    }

    /**
     * Returns the map of nodes and their associated data.
     *
     * @return a HashMap containing the node ids and data objects
     */
    public HashMap<Integer, Object> getNodes() {
        return nodes;
    }

    /*
     * Performs a depth-first search (DFS) traversal starting from the specified
     * node.
     *
     */
    private void dfs(int node, boolean[] visited) {
        visited[node] = true;

        for (int i = 0; i < numNodes; i++) {
            if (!visited[i] && matrix[node][i] != 0) {
                dfs(i, visited);
            }
        }
    }
}
