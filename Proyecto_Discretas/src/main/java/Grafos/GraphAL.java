package Grafos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/*
 * Profesora: Adriana Collaguazo Jaramillo
 */
public class GraphAL<V, E> {  // V vértice caracteriza si son Personas, E caracteriza la información de los Arcos
   private LinkedList<Vertex<V, E>> vertices; // Lista de la clase Vertex, sera un vértice que almacene a una Persona
   private boolean isDirected;
   private Comparator<V> cmp; // Vértice nuevo esta repetido formando parte del grafo
    public GraphAL(boolean isDirected, Comparator<V> cmp) {
        this.isDirected = isDirected;
        this.cmp = cmp;
        this.vertices = new LinkedList<>();
    }
    
    public boolean addVertex(V content){
        
        if (content == null || findVertex(content) != null){
            return false;
        }
        // Añadir a la Persona en un vértice
        Vertex<V, E> newVertex = new Vertex<>(content);
        this.vertices.add(newVertex);
        
        return true;
    }

    public Vertex<V, E> findVertex(V content) {
        for (Vertex<V, E> v : vertices){
            V c = v.getContent();
            
            if (this.cmp.compare(c, content) == 0){
                return v;
            }
        }
        return null;
    }
    
    // Actividad en clase
    public boolean connect(V content1, V content2, int weight, E data){
        
        // Caso 1: Contenidos son nulos
        if (content1==null || content2==null){
            return false;
        }
        
        // Búsqueda de vertices por contenido
        Vertex<V, E> v1= findVertex(content1);
        Vertex<V, E> v2= findVertex(content2);
        
        // Vértices no existen
        if (v1==null || v2 == null){
            return false;
        }
        
        // Crear arco y añadir a la lista de adyacencia de v1 source
        Edge<E, V> newEdge = new Edge<>(v1, v2, weight, data);
        v1.getEdges().add(newEdge);
        
        
        if (!this.isDirected){
            Edge<E, V> reverseEdge = new Edge<>(v2, v1, weight, data);
            
            // También añadir a la lista de adyacencia de v2
            v2.getEdges().add(reverseEdge);
        }
        
        return true;
    }
    
        // Método BFS para recorrer el grafo por anchura
    public void bfs(V startContent) {
        Vertex<V, E> startVertex = findVertex(startContent);
        if (startVertex == null) {
            System.out.println("El vértice de inicio no existe.");
            return;
        }

        Set<Vertex<V, E>> visited = new HashSet<>();
        Queue<Vertex<V, E>> queue = new LinkedList<>();

        // Inicializar el recorrido
        visited.add(startVertex);
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            Vertex<V, E> current = queue.poll();
            System.out.println("Visitando vértice: " + current.getContent());

            // Recorrer los vecinos del vértice actual
            for (Edge<E, V> edge : current.getEdges()) {
                Vertex<V, E> neighbor = edge.getTarget();
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }
    
        // Método DFS para recorrer el grafo por profundidad
    public void dfs(V startContent) {
        Vertex<V, E> startVertex = findVertex(startContent);
        if (startVertex == null) {
            System.out.println("El vértice de inicio no existe.");
            return;
        }

        Set<Vertex<V, E>> visited = new HashSet<>();
        Stack<Vertex<V, E>> stack = new Stack<>();

        // Inicializar el recorrido
        stack.push(startVertex);

        while (!stack.isEmpty()) {
            Vertex<V, E> current = stack.pop();
            if (visited.contains(current)) {
                continue;
            }

            visited.add(current);
            System.out.println("Visitando vértice: " + current.getContent());

            // Añadir los vecinos al stack (para explorar más tarde)
            for (Edge<E, V> edge : current.getEdges()) {
                Vertex<V, E> neighbor = edge.getTarget();
                if (!visited.contains(neighbor)) {
                    stack.push(neighbor);
                }
            }
        }
    }
    
    // Método para encontrar componentes conexas
    public List<Set<Vertex<V, E>>> getConnectedComponents() {
        Set<Vertex<V, E>> visited = new HashSet<>();
        List<Set<Vertex<V, E>>> components = new ArrayList<>();

        for (Vertex<V, E> vertex : vertices) {
            if (!visited.contains(vertex)) {
                Set<Vertex<V, E>> component = new HashSet<>();
                dfs(vertex, visited, component);
                components.add(component);
            }
        }

        return components;
    }
    
    
    // Método para encontrar componentes fuertemente conexas
    public List<Set<Vertex<V, E>>> getStronglyConnectedComponents() {
        List<Set<Vertex<V, E>>> components = new ArrayList<>();
        Stack<Vertex<V, E>> stack = new Stack<>();
        Set<Vertex<V, E>> visited = new HashSet<>();

        // 1. Realizar DFS y almacenar vértices en el stack por el orden de finalización
        for (Vertex<V, E> vertex : vertices) {
            if (!visited.contains(vertex)) {
                fillOrder(vertex, visited, stack);
            }
        }

        // 2. Transponer el grafo
        GraphAL<V, E> transposedGraph = getTranspose();

        // 3. Realizar DFS en el grafo transpuesto en el orden del stack
        visited.clear();
        while (!stack.isEmpty()) {
            Vertex<V, E> vertex = stack.pop();
            if (!visited.contains(vertex)) {
                Set<Vertex<V, E>> component = new HashSet<>();
                transposedGraph.dfs(vertex, visited, component);
                components.add(component);
            }
        }

        return components;
    }

    // Método para llenar el stack con el orden de finalización de DFS
    private void fillOrder(Vertex<V, E> vertex, Set<Vertex<V, E>> visited, Stack<Vertex<V, E>> stack) {
        visited.add(vertex);
        for (Edge<E, V> edge : vertex.getEdges()) {
            Vertex<V, E> neighbor = edge.getTarget();
            if (!visited.contains(neighbor)) {
                fillOrder(neighbor, visited, stack);
            }
        }
        stack.push(vertex);
    }

    // Método para obtener el grafo transpuesto
    private GraphAL<V, E> getTranspose() {
        GraphAL<V, E> transposedGraph = new GraphAL<>(true, this.cmp);
        for (Vertex<V, E> vertex : vertices) {
            transposedGraph.addVertex(vertex.getContent());
        }
        for (Vertex<V, E> vertex : vertices) {
            for (Edge<E, V> edge : vertex.getEdges()) {
                transposedGraph.connect(edge.getTarget().getContent(), vertex.getContent(), edge.getWeight(), edge.getData());
            }
        }
        return transposedGraph;
    }

    // Método DFS auxiliar para encontrar componentes conexas
    private void dfs(Vertex<V, E> startVertex, Set<Vertex<V, E>> visited, Set<Vertex<V, E>> component) {
        Stack<Vertex<V, E>> stack = new Stack<>();
        stack.push(startVertex);

        while (!stack.isEmpty()) {
            Vertex<V, E> current = stack.pop();
            if (visited.contains(current)) {
                continue;
            }

            visited.add(current);
            component.add(current);

            // Añadir los vecinos al stack
            for (Edge<E, V> edge : current.getEdges()) {
                Vertex<V, E> neighbor = edge.getTarget();
                if (!visited.contains(neighbor)) {
                    stack.push(neighbor);
                }
            }
        }
    }

    public LinkedList<Vertex<V, E>> getVertices() {
        return vertices;
    }
}