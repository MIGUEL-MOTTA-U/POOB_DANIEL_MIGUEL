package domain;
import java.util.*;

public class Grafo {
    private int numNodes;
    private int[][] matrix;
    private HashMap<Integer, Object> nodes;

    public HashMap<Integer, Object> getNodes() {
        return nodes;
    }

    public Grafo(int numNodes) {
        this.numNodes = numNodes;
        this.matrix = new int[numNodes][numNodes];
        this.nodes = new HashMap<>();
    }

    public void addNode(int id, Object dato) {
        nodes.put(id, dato);
    }

    public void addVertex(int nodoA, int nodoB, int peso) {
        matrix[nodoA][nodoB] = peso;
        matrix[nodoB][nodoA] = peso;
    }

    public List<Integer> shortestWay(int nodoA, int nodoB) {
        int[] distances = new int[numNodes];
        boolean[] visited = new boolean[numNodes];
        int[] previos = new int[numNodes];
        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(previos, -1);

        distances[nodoA] = 0;

        for (int i = 0; i < numNodes - 1; i++) {
            int nodeMin = minDistancia(distances, visited);
            visited[nodeMin] = true;

            for (int j = 0; j < numNodes; j++) {
                if (!visited[j] && matrix[nodeMin][j] != 0 && distances[nodeMin] != Integer.MAX_VALUE &&
                        distances[nodeMin] + matrix[nodeMin][j] < distances[j]) {
                    distances[j] = distances[nodeMin] + matrix[nodeMin][j];
                    previos[j] = nodeMin;
                }
            }
        }

        List<Integer> way = new ArrayList<>();
        int currentNode = nodoB;
        while (currentNode != -1) {
            way.add(0, currentNode);
            currentNode = previos[currentNode];
        }

        return way;
    }

    private int minDistancia(int[] distances, boolean[] visited) {
        int minVal = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < numNodes; i++) {
            if (!visited[i] && distances[i] < minVal) {
                minVal = distances[i];
                minIndex = i;
            }
        }

        return minIndex;
    }
}

