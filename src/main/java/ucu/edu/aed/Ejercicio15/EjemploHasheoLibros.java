package ucu.edu.aed.Ejercicio15;
import java.util.HashSet;
import java.util.Set;

public class EjemploHasheoLibros {
    public static void main(String[] args) {
        // Dos objetos distintos que representan el mismo libro (mismo isbn)
        Libro l1 = new Libro("978-842-04-3748-4", "Rayuela", "Julio Cortázar", 1963);
        Libro l2 = new Libro("978-842-04-3748-4", "RAYUELA", "Cortázar, Julio", 1963);

        Set<Libro> libros = new HashSet<>();
        libros.add(l1);
        libros.add(l2);

        System.out.println("l1 == l2:        " + (l1 == l2));       // false: objetos distintos en memoria
        System.out.println("l1.equals(l2):   " + l1.equals(l2));    // true: mismo isbn, mismo libro logicamente
        System.out.println("hashCode iguales: " + (l1.hashCode() == l2.hashCode()));
        System.out.println("Tamaño del HashSet: " + libros.size()); // 1 solo Libro
        System.out.println("libros.contains(l2): " + libros.contains(l2));
    }
}
