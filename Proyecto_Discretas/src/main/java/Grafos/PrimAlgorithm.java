package Grafos;

import java.util.Arrays;
import java.util.PriorityQueue;

public class PrimAlgorithm {

    private int[][] distancias;

    public PrimAlgorithm(int[][] distancias) {
        this.distancias = distancias;
    }

    public int calcularCaminoMinimo(int inicio, int fin) {
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

            for (int v = 0; v < n; v++) {
                if (distancias[u][v] != 0 && !inMST[v] && distancias[u][v] < dist[v]) {
                    dist[v] = distancias[u][v];
                    parent[v] = u;
                    pq.add(new int[]{v, dist[v]});
                }
            }
        }

        return dist[fin] == Integer.MAX_VALUE ? -1 : dist[fin];
    }
}