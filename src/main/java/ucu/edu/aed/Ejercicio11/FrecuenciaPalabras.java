package ucu.edu.aed.Ejercicio11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FrecuenciaPalabras {

    public static void main(String[] args) throws FileNotFoundException {

        HashMap<String, Integer> frecuencias = new HashMap<>();

        Scanner archivo = new Scanner(new File("libro.txt"));

        while (archivo.hasNext()) {

            String palabra = archivo.next()
                    .toLowerCase()
                    .replaceAll("[^a-záéíóúüñ]", "");

            if (!palabra.isEmpty()) {
                frecuencias.put(
                        palabra,
                        frecuencias.getOrDefault(palabra, 0) + 1
                );
            }
        }

        archivo.close();

        List<Map.Entry<String, Integer>> palabras =
                new ArrayList<>(frecuencias.entrySet());

        palabras.sort(
                Map.Entry.comparingByValue(Comparator.reverseOrder())
        );

        System.out.println("Las 10 palabras más frecuentes:");

        int cantidad = Math.min(10, palabras.size());

        for (int i = 0; i < cantidad; i++) {
            Map.Entry<String, Integer> entrada = palabras.get(i);

            System.out.println(
                    (i + 1) + ". " +
                    entrada.getKey() + " : " +
                    entrada.getValue()
            );
        }
    }
}