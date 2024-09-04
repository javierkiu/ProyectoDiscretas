package Grafos;

import java.util.LinkedList;


class Vertex<V, E> { // La clase Vertex tendra en el parametro V información como Persona y el parametro E información del Arco. 
    private V content; // guarda el valor de 4 - parametro V se usa para definir el origen y destino
    private LinkedList<Edge<E, V>> edges; // Que tipo de dato almacena esta colección seria de tipo Arcos
    // cada vértice tiene una lista de arcos

    public Vertex(V content) {
        this.content = content;
        this.edges = new LinkedList<>(); // Todo vértice nace con un contenido sin lista de arcos (esta vacia)
    }

    public V getContent() {
        return content;
    }

    public void setContent(V content) {
        this.content = content;
    }

    public LinkedList<Edge<E, V>> getEdges() {
        return edges;
    }

    public void setEdges(LinkedList<Edge<E, V>> edges) {
        this.edges = edges;
    }

    @Override
    public String toString() {
        return "Vertex{" + "content=" + content + '}';
    }   
}
