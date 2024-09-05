package Grafos;

import java.util.*;

public class DijkstraAlgorithm {
    private GraphAL<String, Integer> graph;

    public DijkstraAlgorithm(GraphAL<String, Integer> graph) {
        this.graph = graph;
    }

    public String findShortestPath(String start, String end) {
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
        if(Math.abs(distances.get(endVertex)) >= 2147483000){
            return "No hay un camino entre estos dos vertices o el camino es muy largo :(";
        }else{
            // Imprimir el camino más corto y el costo total
            String costo = printPath(predecessors, endVertex);
            costo += ". Con el costo de: "+ distances.get(endVertex);
            return costo;            
        }

    }

    private String printPath(Map<Vertex<String, Integer>, Vertex<String, Integer>> predecessors, Vertex<String, Integer> endVertex) {
        Stack<Vertex<String, Integer>> path = new Stack<>();
        Vertex<String, Integer> current = endVertex;

        while (current != null) {
            path.push(current);
            current = predecessors.get(current);
        }

        StringBuilder sb = new StringBuilder();
        while (!path.isEmpty()) {
            sb.append(path.pop().getContent());
            if (!path.isEmpty()) {
                sb.append(" -> ");
            }
        }
        return sb.toString( );
    }
}