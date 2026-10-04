package ucu.edu.aed.Ejercicio11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Cuenta cuántas veces aparece cada palabra del libro y grafica las 10 más frecuentes.
 */
public class FrecuenciaPalabras {

    private static final String ARCHIVO_LIBRO = "src/main/resources/ut03/libro.txt";

    public static void main(String[] args) throws FileNotFoundException {

        HashMap<String, Integer> frecuencias = new HashMap<>(); // palabra -> cantidad de veces

        Scanner archivo = new Scanner(new File(ARCHIVO_LIBRO), "UTF-8");

        while (archivo.hasNext()) {

            String palabra = archivo.next()
                    .toLowerCase()
                    .replaceAll("[^a-záéíóúüñ]", ""); // así "Faro," y "faro" cuentan igual

            if (!palabra.isEmpty()) {
                frecuencias.put(
                        palabra,
                        frecuencias.getOrDefault(palabra, 0) + 1 // si no estaba arranca en 0
                );
            }
        }

        archivo.close();

        List<Map.Entry<String, Integer>> palabras =
                new ArrayList<>(frecuencias.entrySet());

        // el HashMap no tiene orden, se pasa a una lista y se ordena de mayor a menor
        palabras.sort(
                Map.Entry.comparingByValue(Comparator.reverseOrder())
        );

        System.out.println("Palabras distintas: " + palabras.size());
        System.out.println("Las 10 palabras más frecuentes:");

        int cantidad = Math.min(10, palabras.size());
        List<Map.Entry<String, Integer>> top = palabras.subList(0, cantidad);

        for (int i = 0; i < cantidad; i++) {
            Map.Entry<String, Integer> entrada = top.get(i);

            System.out.println(
                    (i + 1) + ". " +
                    entrada.getKey() + " : " +
                    entrada.getValue()
            );
        }

        graficarConsola(top);
    }

    // gráfico de barras en la consola, la barra más larga mide 50 '#'
    public static void graficarConsola(List<Map.Entry<String, Integer>> top) {
        int maximo = top.get(0).getValue();
        System.out.println("\nGráfico:");
        for (Map.Entry<String, Integer> entrada : top) {
            String barra = "";
            for (int i = 0; i < entrada.getValue() * 50 / maximo; i++) {
                barra += "#";
            }
            System.out.printf("%-8s | %s %d%n", entrada.getKey(), barra, entrada.getValue());
        }
    }
}
