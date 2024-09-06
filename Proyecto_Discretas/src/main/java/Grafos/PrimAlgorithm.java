package Grafos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class PrimAlgorithm {
    private int[][] distancias;
    private List<int[]>[] mst;  // Lista de adyacencia para almacenar el MST

    public PrimAlgorithm(int[][] distancias) {
        this.distancias = distancias;
        this.mst = new ArrayList[distancias.length];
        for (int i = 0; i < distancias.length; i++) {
            mst[i] = new ArrayList<>();
        }
    }

    public void construirMST(int inicio) {
        int n = distancias.length;
        int[] dist = new int[n];
        boolean[] inMST = new boolean[n];
        int[] parent = new int[n];

        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));

        dist[inicio] = 0;
        pq.add(new int[]{inicio, 0});

        while (!pq.isEmpty()) {
            int u = pq.poll()[0];
            if (inMST[u]) continue;
            inMST[u] = true;

            // Añadir el nodo al MST
            if (parent[u] != -1) {
                mst[u].add(new int[]{parent[u], dist[u]});
                mst[parent[u]].add(new int[]{u, dist[u]});
            }
            for (int v = 0; v < n; v++) {
                if (distancias[u][v] != 0 && !inMST[v] && distancias[u][v] < dist[v]) {
                    dist[v] = distancias[u][v];
                    parent[v] = u;
                    pq.add(new int[]{v, dist[v]});
                }
            }
        }
    }

    public int calcularCaminoEnMST(int inicio, int fin) {
        boolean[] visitado = new boolean[distancias.length];
        return dfs(inicio, fin, visitado);
    }

    private int dfs(int actual, int fin, boolean[] visitado) {
        if (actual == fin) return 0;
        visitado[actual] = true;
        for (int[] vecino : mst[actual]) {
            int nodoVecino = vecino[0];
            int peso = vecino[1];

            if (!visitado[nodoVecino]) {
                int resultado = dfs(nodoVecino, fin, visitado);
                if (resultado != -1) {
                    return peso + resultado;
                }
            }
        }
        return -1;
    }
}