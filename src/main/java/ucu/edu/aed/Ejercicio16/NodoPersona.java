package ucu.edu.aed.Ejercicio16;

import java.util.ArrayList;
import java.util.List;

/**
 * Nodo del árbol genérico: una persona y la lista de sus hijos (cero o más).
 */
public class NodoPersona {
    private Persona persona;
    private List<NodoPersona> hijos = new ArrayList<>();

    public NodoPersona(Persona persona) {
        this.persona = persona;
    }

    public Persona getPersona() {
        return persona;
    }

    public NodoPersona agregarHijo(Persona p) {
        NodoPersona hijo = new NodoPersona(p);
        hijos.add(hijo);
        return hijo;
    }

    // Busca por nombre en este nodo y en sus descendientes
    public NodoPersona buscar(String nombre) {
        if (persona.getNombre().equals(nombre)) {
            return this;
        }
        for (NodoPersona h : hijos) {
            NodoPersona r = h.buscar(nombre);
            if (r != null) {
                return r;
            }
        }
        return null;
    }

    // Agrega a la lista todos los descendientes (sin incluir a esta persona)
    public void descendientes(List<Persona> lista) {
        for (NodoPersona h : hijos) {
            lista.add(h.persona);
            h.descendientes(lista);
        }
    }

    // Una persona sin hijos tiene altura 0
    public int altura() {
        int max = -1;
        for (NodoPersona h : hijos) {
            max = Math.max(max, h.altura());
        }
        return max + 1;
    }

    public int contar() {
        int c = 1;
        for (NodoPersona h : hijos) {
            c += h.contar();
        }
        return c;
    }

    // Generación 0 es este nodo, 1 sus hijos, etc.
    public void generacion(int g, List<Persona> lista) {
        if (g == 0) {
            lista.add(persona);
            return;
        }
        for (NodoPersona h : hijos) {
            h.generacion(g - 1, lista);
        }
    }

    // Si a y b están en ramas de hijos distintos, este nodo es el ancestro común.
    // Si los dos están en la rama del mismo hijo, la respuesta viene de ese hijo.
    // Si este nodo es a o b, él mismo es el ancestro común.
    public NodoPersona ancestroComun(String a, String b) {
        if (persona.getNombre().equals(a) || persona.getNombre().equals(b)) {
            return this;
        }
        NodoPersona encontrado = null;
        int cuantos = 0;
        for (NodoPersona h : hijos) {
            NodoPersona r = h.ancestroComun(a, b);
            if (r != null) {
                encontrado = r;
                cuantos++;
            }
        }
        if (cuantos == 2) {
            return this;
        }
        return encontrado;
    }
}
