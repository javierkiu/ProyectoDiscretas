package Grafos;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Steven Mirabá
 */
public class BruteForceTSPAlgorithm {
    // Matriz de distancias
    private int[][] distancias;
    /*= {
        {0, 1023, 0, 0, 0, 0, 0, 0, 0, 3597},
        {1023, 0, 734, 1885, 0, 0, 0, 0, 0, 2666},
        {0, 734, 0, 1324, 0, 0, 0, 0, 0, 0},
        {0, 1885, 1324, 0, 2466, 0, 1077, 0, 0, 3171},
        {0, 0, 0, 2466, 0, 1138, 1900, 0, 0, 0},
        {0, 0, 0, 0, 1138, 0, 2237, 1040, 202, 2340},
        {0, 0, 0, 1077, 1900, 2237, 0, 1464, 0, 2165},
        {0, 0, 0, 0, 0, 1040, 1464, 0, 0, 1463},
        {0, 0, 0, 0, 0, 202, 0, 0, 0, 2770},
        {3597, 2666, 0, 3171, 0, 2340, 2165, 1463, 2770, 0}
    };*/

    // Número de vértices
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

    private int calcularPeso(List<Integer> ciclo) {
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

