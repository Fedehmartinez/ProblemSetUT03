package ucu.edu.aed.Ejercicio12;

import ucu.edu.aed.tda.trie.Entry;
import ucu.edu.aed.tda.trie.impl.TTrieHashMap;
import ucu.edu.aed.utils.FileUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Carga el listado de palabras en el trie y sugiere palabras a partir de lo que se escribe.
 */
public class Autocompletar {

    private static final String ARCHIVO = "./ut03/listado-general-desordenado.txt";
    private static final int MAX_SUGERENCIAS = 10;

    public static void main(String[] args) {
        TTrieHashMap<String> trie = new TTrieHashMap<>();
        int[] cantidad = {0};
        FileUtils.leerLineas(ARCHIVO, linea -> {
            String palabra = linea.trim().toLowerCase();
            if (!palabra.isEmpty() && trie.insertar(palabra, palabra)) {
                cantidad[0]++;
            }
        });
        System.out.println("Palabras cargadas: " + cantidad[0]);

        Scanner scanner = new Scanner(System.in);
        String prefijo = leerPrefijo(scanner);
        while (!prefijo.isEmpty()) {
            List<Entry<String>> sugerencias = trie.predecir(prefijo);
            sugerencias.sort(Comparator.comparing(Entry::getPalabra)); // orden alfabético

            if (sugerencias.isEmpty()) {
                System.out.println("No hay palabras que empiecen con \"" + prefijo + "\"");
            } else {
                System.out.println(sugerencias.size() + " palabras empiezan con \"" + prefijo + "\":");
                int i = 0;
                while (i < sugerencias.size() && i < MAX_SUGERENCIAS) {
                    System.out.println("  " + sugerencias.get(i).getPalabra());
                    i++;
                }
                if (sugerencias.size() > MAX_SUGERENCIAS) {
                    System.out.println("  ...");
                }
            }
            prefijo = leerPrefijo(scanner);
        }
    }

    // devuelve "" si el usuario no escribe nada (así se sale)
    private static String leerPrefijo(Scanner scanner) {
        System.out.print("\nEscribi el comienzo de una palabra (enter para salir): ");
        if (!scanner.hasNextLine()) {
            return "";
        }
        return scanner.nextLine().trim().toLowerCase();
    }
}
