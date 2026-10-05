package ucu.edu.aed.Ejercicio16;

import java.util.ArrayList;
import java.util.List;

/**
 * Árbol genealógico construido desde un ancestro común (la raíz).
 */
public class ArbolGenealogico {
    private NodoPersona raiz;

    public ArbolGenealogico(Persona ancestro) {
        raiz = new NodoPersona(ancestro);
    }

    public NodoPersona getRaiz() {
        return raiz;
    }

    // 1. Todos los descendientes de una persona
    public List<Persona> descendientes(String nombre) {
        List<Persona> lista = new ArrayList<>();
        NodoPersona n = raiz.buscar(nombre);
        if (n != null) {
            n.descendientes(lista);
        }
        return lista;
    }

    // 2. Altura del árbol
    public int altura() {
        return raiz.altura();
    }

    // 3. Cantidad total de personas
    public int cantidadPersonas() {
        return raiz.contar();
    }

    // 4. Personas de una generación (0 = raíz)
    public List<Persona> generacion(int g) {
        List<Persona> lista = new ArrayList<>();
        raiz.generacion(g, lista);
        return lista;
    }

    // 5. Ancestro común más cercano (si alguna de las dos no existe devuelve null)
    public Persona ancestroComun(String a, String b) {
        if (raiz.buscar(a) == null || raiz.buscar(b) == null) {
            return null;
        }
        return raiz.ancestroComun(a, b).getPersona();
    }

    // 6. Indica si desc es descendiente de anc
    public boolean esDescendiente(String desc, String anc) {
        NodoPersona a = raiz.buscar(anc);
        if (a == null || anc.equals(desc)) {
            return false;
        }
        return a.buscar(desc) != null;
    }
}
