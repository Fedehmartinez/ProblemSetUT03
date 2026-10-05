package ucu.edu.aed.Ejercicio16;

public class Main {

    public static void main(String[] args) {
        // Generación 0
        ArbolGenealogico arbol = new ArbolGenealogico(new Persona("Rosa", 1940));
        NodoPersona rosa = arbol.getRaiz();

        // Generación 1
        NodoPersona carlos = rosa.agregarHijo(new Persona("Carlos", 1965));
        NodoPersona marta = rosa.agregarHijo(new Persona("Marta", 1968));
        NodoPersona jorge = rosa.agregarHijo(new Persona("Jorge", 1972));

        // Generación 2
        carlos.agregarHijo(new Persona("Lucia", 1990));
        carlos.agregarHijo(new Persona("Pablo", 1993));
        marta.agregarHijo(new Persona("Sofia", 1995));
        marta.agregarHijo(new Persona("Diego", 1997));
        marta.agregarHijo(new Persona("Ana", 2000));
        jorge.agregarHijo(new Persona("Tomas", 2002));

        System.out.println("Descendientes de Marta: " + arbol.descendientes("Marta"));
        System.out.println("Descendientes de Rosa: " + arbol.descendientes("Rosa"));
        System.out.println("Altura del árbol: " + arbol.altura());
        System.out.println("Cantidad de personas: " + arbol.cantidadPersonas());
        System.out.println("Generación 0: " + arbol.generacion(0));
        System.out.println("Generación 1: " + arbol.generacion(1));
        System.out.println("Generación 2: " + arbol.generacion(2));
        System.out.println("Ancestro común de Lucia y Pablo: " + arbol.ancestroComun("Lucia", "Pablo"));
        System.out.println("Ancestro común de Lucia y Ana: " + arbol.ancestroComun("Lucia", "Ana"));
        System.out.println("Ancestro común de Marta y Diego: " + arbol.ancestroComun("Marta", "Diego"));
        System.out.println("¿Sofia es descendiente de Marta? " + arbol.esDescendiente("Sofia", "Marta"));
        System.out.println("¿Sofia es descendiente de Carlos? " + arbol.esDescendiente("Sofia", "Carlos"));
        System.out.println("¿Rosa es descendiente de Tomas? " + arbol.esDescendiente("Rosa", "Tomas"));
    }
}
