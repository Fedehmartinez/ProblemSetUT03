package ucu.edu.aed.Ejercicio15;

//Ejercicio 15 - Punto 6: casos de prueba del contrato de equals y hashCode de Libro.


public class LibroTest {

    private static int fallos = 0;

    private static void verificar(String caso, boolean condicion) {
        System.out.println((condicion ? "OK : " : "FALLA : ") + caso);
        if (!condicion) fallos++;
    }

    public static void main(String[] args) {
        Libro a = new Libro("978-950-07-1234-5", "Rayuela", "Julio Cortázar", 1963);
        Libro b = new Libro("978-950-07-1234-5", "RAYUELA", "Cortázar, Julio", 1963);
        Libro c = new Libro("978-950-07-1234-5", "Rayuela (ed. 2)", "J. Cortázar", 2000);
        Libro otro = new Libro("978-84-376-0494-7", "Rayuela", "Julio Cortázar", 1963);

        System.out.println("== Testeo equals() ==");
        verificar("1. a.equals(a)", a.equals(a));
        verificar("2. a.equals(b) == b.equals(a)", a.equals(b) == b.equals(a) && a.equals(b));
        verificar("3. a=b y b=c => a=c", a.equals(b) && b.equals(c) && a.equals(c));
        verificar("4. Consistente: llamadas repetidas dan el mismo resultado",
                a.equals(b) == a.equals(b) && a.equals(otro) == a.equals(otro));
        verificar("5. Null: a.equals(null) es false", !a.equals(null));
        verificar("6. Otra clase: a.equals(String) es false", !a.equals("978-950-07-1234-5"));
        verificar("7. Distinto isbn => no iguales", !a.equals(otro));
        verificar("8. Mismo isbn, titulo/autor/anio distintos => iguales", a.equals(c));

        System.out.println("== Testeo hashCode() ==");
        verificar("9. Consistente: mismo objeto, mismo hashCode", a.hashCode() == a.hashCode());
        verificar("10. Si a.equals(b) => mismo hashCode", a.equals(b) && a.hashCode() == b.hashCode());
        verificar("11. Si a.equals(c) => mismo hashCode", a.equals(c) && a.hashCode() == c.hashCode());
        a.setTitulo("Otro titulo");
        verificar("12. Cambiar atributos que no son identidad no cambia el hashCode", a.hashCode() == b.hashCode());

        System.out.println("- Libros isbn nulo -");
        Libro n1 = new Libro(null, "Sin isbn", "Anonimo", 2020);
        Libro n2 = new Libro(null, "Otro sin isbn", "Anonimo", 2021);
        verificar("13. isbn null: equals no lanza excepción y compara bien", n1.equals(n2) && !n1.equals(a));
        verificar("14. isbn null: hashCode no lanza excepción y es consistente", n1.hashCode() == n2.hashCode());

        System.out.println();
        System.out.println(fallos == 0 ? "Todos los casos pasaron." : "Casos fallidos: " + fallos);
    }
}