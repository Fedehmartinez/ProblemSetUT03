package ucu.edu.aed.Ejercicio12;

import ucu.edu.aed.utils.FileUtils;

import java.util.List;
import java.util.Scanner;

/**
 * Arma el árbol de sufijos de un texto y muestra en qué posiciones aparece cada patrón.
 */
public class BuscarPatrones {

    private static final String ARCHIVO = "./ut03/texto-patrones.txt";

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        FileUtils.leerLineas(ARCHIVO, linea -> sb.append(linea).append(' '));
        String texto = sb.toString().trim().toLowerCase(); // así no importan las mayúsculas

        ArbolSufijos arbol = new ArbolSufijos(texto);
        System.out.println("Texto cargado (" + texto.length() + " caracteres):");
        System.out.println(texto);

        Scanner scanner = new Scanner(System.in);
        String patron = leerPatron(scanner);
        while (!patron.isEmpty()) {
            List<Integer> posiciones = arbol.buscarPatron(patron);
            if (posiciones.isEmpty()) {
                System.out.println("\"" + patron + "\" no aparece en el texto");
            } else {
                System.out.println("\"" + patron + "\" aparece " + posiciones.size() + " veces, en las posiciones " + posiciones);
                for (int pos : posiciones) {
                    System.out.println("  " + pos + ": " + fragmento(texto, pos, patron.length()));
                }
            }
            patron = leerPatron(scanner);
        }
    }

    // devuelve "" si el usuario no escribe nada (así se sale)
    private static String leerPatron(Scanner scanner) {
        System.out.print("\nPatron a buscar (enter para salir): ");
        if (!scanner.hasNextLine()) {
            return "";
        }
        return scanner.nextLine().toLowerCase();
    }

    // el patrón entre corchetes con un poco de texto alrededor
    private static String fragmento(String texto, int pos, int largo) {
        int desde = Math.max(0, pos - 15);
        int hasta = Math.min(texto.length(), pos + largo + 15);
        return "..." + texto.substring(desde, pos)
                + "[" + texto.substring(pos, pos + largo) + "]"
                + texto.substring(pos + largo, hasta) + "...";
    }
}
