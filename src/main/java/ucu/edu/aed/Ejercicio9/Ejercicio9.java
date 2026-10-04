package ucu.edu.aed.Ejercicio9;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

/**
 * Llena la tabla hash de a poco y mide las comparaciones promedio en cada factor de carga.
 */
public class Ejercicio9 {

    private static final int M = 100_003; // tamaño de la tabla, primo
    private static final int[] FACTORES = {70, 75, 80, 85, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99};
    private static final int CLAVES_SIN_EXITO = 20_000;
    private static final long SEMILLA = 2026; // misma semilla = mismos resultados en cada corrida

    public static void main(String[] args) {
        int maxInsertar = (int) Math.round(M * 0.99);

        // las primeras se insertan, las últimas 20.000 nunca (sirven para las búsquedas sin éxito)
        int[] claves = generarClavesDistintas(maxInsertar + CLAVES_SIN_EXITO, new Random(SEMILLA));

        TablaHashLineal tabla = new TablaHashLineal(M);
        int insertadas = 0;

        System.out.println("Tabla de M = " + M + " posiciones, sondeo lineal, h(0) = clave mod M");
        System.out.printf("%-8s %12s %12s %12s %16s %16s%n",
                "Factor", "Inserción", "Exitosa", "Sin éxito", "Teórica exitosa", "Teórica sin éxito");

        for (int factor : FACTORES) {
            int objetivo = (int) Math.round(M * factor / 100.0);

            // inserción: promedio de las claves agregadas desde el factor anterior hasta este
            long compInsercion = 0;
            int desde = insertadas;
            while (insertadas < objetivo) {
                compInsercion += tabla.insertar(claves[insertadas]);
                insertadas++;
            }

            long compExito = 0;
            for (int i = 0; i < insertadas; i++) {
                compExito += tabla.buscar(claves[i]);
            }

            long compSinExito = 0;
            for (int i = maxInsertar; i < claves.length; i++) {
                compSinExito += tabla.buscar(claves[i]);
            }

            double alfa = (double) insertadas / M;
            System.out.printf("%-8s %12.2f %12.2f %12.2f %16.2f %16.2f%n",
                    factor + "%",
                    (double) compInsercion / (insertadas - desde),
                    (double) compExito / insertadas,
                    (double) compSinExito / CLAVES_SIN_EXITO,
                    0.5 * (1 + 1 / (1 - alfa)),                     // ½(1 + 1/(1-α))
                    0.5 * (1 + 1 / ((1 - alfa) * (1 - alfa))));    // ½(1 + 1/(1-α)²)
        }
    }

    // el set descarta las repetidas y mantiene el orden en que se generaron
    private static int[] generarClavesDistintas(int cantidad, Random random) {
        Set<Integer> claves = new LinkedHashSet<>();
        while (claves.size() < cantidad) {
            claves.add(random.nextInt(Integer.MAX_VALUE));
        }
        int[] resultado = new int[cantidad];
        int i = 0;
        for (int clave : claves) {
            resultado[i] = clave;
            i++;
        }
        return resultado;
    }
}
