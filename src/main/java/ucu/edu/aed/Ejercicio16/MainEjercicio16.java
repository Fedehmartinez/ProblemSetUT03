package ucu.edu.aed.Ejercicio16;

public class MainEjercicio16 {
  public static void main(String[] args) {
    Persona rosa = new Persona("Rosa", 1940);
    Persona carlos = new Persona("Carlos", 1962);
    Persona marta = new Persona("Marta", 1965);
    Persona jorge = new Persona("Jorge", 1968);
    Persona ana = new Persona("Ana", 1988);
    Persona luis = new Persona("Luis", 1990);
    Persona sofia = new Persona("Sofía", 1992);
    Persona pedro = new Persona("Pedro", 1995);
    Persona lucia = new Persona("Lucía", 1997);
    Persona tomas = new Persona("Tomás", 2020);

    ArbolGenealogico arbol = new ArbolGenealogico(rosa);
    arbol.agregarHijo(rosa, carlos);
    arbol.agregarHijo(rosa, marta);
    arbol.agregarHijo(rosa, jorge);
    arbol.agregarHijo(carlos, ana);
    arbol.agregarHijo(carlos, luis);
    arbol.agregarHijo(marta, sofia);
    arbol.agregarHijo(marta, pedro);
    arbol.agregarHijo(jorge, lucia);
    arbol.agregarHijo(ana, tomas);

    System.out.println("Descendientes de Carlos: " + arbol.listarDescendientes(carlos));
    System.out.println("Altura: " + arbol.altura());
    System.out.println("Total de personas: " + arbol.contarPersonas());
    System.out.println("Generación 2: " + arbol.personasDeGeneracion(2));
    System.out.println("Ancestro común de Tomás y Luis: " + arbol.ancestroComun(tomas, luis));
    System.out.println("Ancestro común de Tomás y Pedro: " + arbol.ancestroComun(tomas, pedro));
    System.out.println("¿Tomás desciende de Carlos? " + arbol.esDescendiente(tomas, carlos));
    System.out.println("¿Tomás desciende de Marta? " + arbol.esDescendiente(tomas, marta));
  }
}