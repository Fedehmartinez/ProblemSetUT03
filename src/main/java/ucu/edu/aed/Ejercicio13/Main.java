package ucu.edu.aed.Ejercicio13;

/**
 * Parte 2: muestra en qué bucket queda cada string en un HashMap de capacidad 16.
 */
public class Main {

    public static void main(String[] args) {
        String[] palabras = {"Hola", "HolaMundo", "HashMap", "Colecciones"};
        int capacidad = 16;
        String[] tabla = new String[capacidad];

        System.out.println("Cálculo del bucket:");
        for (String p : palabras) {
            int h = p.hashCode();
            int hash = h ^ (h >>> 16);          // mezcla los bits altos con los bajos, como HashMap.hash()
            int bucket = (capacidad - 1) & hash; // últimos 4 bits, es como hacer hash % 16
            tabla[bucket] = p;
            System.out.println(p + ": hashCode = " + h + ", bucket = " + bucket);
        }

        System.out.println();
        System.out.println("Tabla después de insertar:");
        for (int i = 0; i < capacidad; i++) {
            if (tabla[i] == null) {
                System.out.println("[" + i + "] null");
            } else {
                System.out.println("[" + i + "] -> Node(\"" + tabla[i] + "\") -> null");
            }
        }
    }
}
