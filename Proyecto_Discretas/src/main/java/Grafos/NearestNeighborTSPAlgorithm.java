package Grafos;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Steven Mirabá
 * Implementación del algoritmo del vecino más cercano para el problema del vendedor viajero (TSP).
 */
public class NearestNeighborTSPAlgorithm {
    private int[][] distancias;

    public NearestNeighborTSPAlgorithm(int[][] distancias) {
        this.distancias = distancias;
    }

    public List<Integer> hamiltonGraph() {
        int n = distancias.length;
        List<Integer> recorrido = new ArrayList<>();
        boolean[] visitado = new boolean[n];
        int nodoActual = 0;
        recorrido.add(nodoActual);
        visitado[nodoActual] = true;
        for (int i = 1; i < n; i++) {
            int siguienteNodo = -1;
            int distanciaMinima = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                if (!visitado[j] && distancias[nodoActual][j] > 0 && distancias[nodoActual][j] < distanciaMinima) {
                    distanciaMinima = distancias[nodoActual][j];
                    siguienteNodo = j;
                }
            }
            if (siguienteNodo == -1) {
                System.out.println("No se encontró un siguiente nodo conectado directamente.");
                return recorrido;
            }
            recorrido.add(siguienteNodo);
            visitado[siguienteNodo] = true;
            nodoActual = siguienteNodo;
        }
        recorrido.add(recorrido.get(0));
        return recorrido;
    }

    public int calcularPeso(List<Integer> ciclo) {
        int peso = 0;
        for (int i = 0; i < ciclo.size() - 1; i++) {
            int u = ciclo.get(i);
            int v = ciclo.get(i + 1);
            if (distancias[u][v] == 0) {
                return Integer.MAX_VALUE;
            }
            peso += distancias[u][v];
        }
        return peso;
    }
}
