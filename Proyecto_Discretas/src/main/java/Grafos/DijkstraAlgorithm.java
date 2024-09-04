package Grafos;

import java.util.*;

public class DijkstraAlgorithm {
    private GraphAL<String, Integer> graph;

    public DijkstraAlgorithm(GraphAL<String, Integer> graph) {
        this.graph = graph;
    }

    public void findShortestPath(String start, String end) {
        // Obtener el vértice de inicio y fin
        Vertex<String, Integer> startVertex = graph.findVertex(start);
        Vertex<String, Integer> endVertex = graph.findVertex(end);

        Map<Vertex<String, Integer>, Integer> distances = new HashMap<>();
        Map<Vertex<String, Integer>, Vertex<String, Integer>> predecessors = new HashMap<>();
        PriorityQueue<Vertex<String, Integer>> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(distances::get));

        for (Vertex<String, Integer> vertex : graph.getVertices()) {
            distances.put(vertex, Integer.MAX_VALUE);
            predecessors.put(vertex, null);
            priorityQueue.add(vertex);
        }

        distances.put(startVertex, 0);
        priorityQueue.add(startVertex);

        // Algoritmo de Dijkstra
        while (!priorityQueue.isEmpty()) {
            Vertex<String, Integer> current = priorityQueue.poll();

            if (current.equals(endVertex)) {
                break;
            }

            for (Edge<Integer, String> edge : current.getEdges()) {
                Vertex<String, Integer> neighbor = edge.getTarget();
                int newDist = distances.get(current) + edge.getWeight();

                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    predecessors.put(neighbor, current);
                    priorityQueue.add(neighbor);
                }
            }
        }

        // Imprimir el camino más corto y el costo total
        printPath(predecessors, endVertex);
        System.out.println("Costo total: " + distances.get(endVertex));
    }

    private void printPath(Map<Vertex<String, Integer>, Vertex<String, Integer>> predecessors, Vertex<String, Integer> endVertex) {
        Stack<Vertex<String, Integer>> path = new Stack<>();
        Vertex<String, Integer> current = endVertex;

        while (current != null) {
            path.push(current);
            current = predecessors.get(current);
        }

        System.out.print("Camino más corto: ");
        while (!path.isEmpty()) {
            System.out.print(path.pop().getContent());
            if (!path.isEmpty()) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }
}