package Grafos;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Steven Mirabá
 */
public class BruteForceTSPAlgorithm {
    private int[][] distancias;

    public BruteForceTSPAlgorithm(int[][] distancias) {
        this.distancias = distancias;
    }

    public List<Integer> hamiltonGraph() {
        List<Integer> cicloMinimo = null;
        int pesoMinimo = Integer.MAX_VALUE;
        int n = distancias.length;
        List<Integer> vertices = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            vertices.add(i);
        }     
        List<List<Integer>> permutaciones = new ArrayList<>();
        generarPermutaciones(vertices, 0, permutaciones);       
        for (List<Integer> perm : permutaciones) {
            int pesoActual = calcularPeso(perm);
            if (pesoActual < pesoMinimo) {
                pesoMinimo = pesoActual;
                cicloMinimo = new ArrayList<>(perm);
            }
        }     
        return cicloMinimo;
    }

    private void generarPermutaciones(List<Integer> lista, int start, List<List<Integer>> result) {
        if (start == lista.size() - 1) {
            result.add(new ArrayList<>(lista));
        } else {
            for (int i = start; i < lista.size(); i++) {
                swap(lista, start, i);
                generarPermutaciones(lista, start + 1, result);
                swap(lista, start, i); // backtrack
            }
        }
    }

    private void swap(List<Integer> lista, int i, int j) {
        int temp = lista.get(i);
        lista.set(i, lista.get(j));
        lista.set(j, temp);
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
        int u = ciclo.get(ciclo.size() - 1);
        int v = ciclo.get(0);
        if (distancias[u][v] == 0) {
            return Integer.MAX_VALUE;
        }
        peso += distancias[u][v];
        return peso;
    }
}