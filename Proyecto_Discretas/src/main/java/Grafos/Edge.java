package Grafos;

/**
 * Profesora: Adriana Collaguazo Jaramillo
 */
class Edge<E, V> { // los atributos de la clase Edge son 4
    private Vertex<V, E> source; // vértice origen, el segundo parametro V se usa para definir el origen y destino
    private Vertex<V, E> target; // vértice destino
    private int weight; // factor de peso
    private E data; // la clase Edge debe ser parametrizada por tipo E para caracterizar la metadata del arco

    public Edge(Vertex<V, E> source, Vertex<V, E> target, int weight, E data) {
        this.source = source;
        this.target = target;
        this.weight = weight;
        this.data = data;
    }

    public Edge(Vertex<V, E> source, Vertex<V, E> target, int weight) {
        this (source, target, weight, null);
    }

    public Edge(Vertex<V, E> source, Vertex<V, E> target, E data) {
        this (source, target, -1, data);
    }

    public Edge(Vertex<V, E> source, Vertex<V, E> target) {
        this (source, target, -1, null);
    }

    public Vertex<V, E> getSource() {
        return source;
    }

    public void setSource(Vertex<V, E> source) {
        this.source = source;
    }

    public Vertex<V, E> getTarget() {
        return target;
    }

    public void setTarget(Vertex<V, E> target) {
        this.target = target;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
      
}