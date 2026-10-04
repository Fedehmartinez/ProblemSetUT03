package ucu.edu.aed.tda.generic_trie.impl;

import ucu.edu.aed.tda.generic_trie.TNodoGenerico;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Nodo de un árbol genérico. Cada nodo apunta a su primer hijo y a su hermano derecho.
 */
public class NodoGenerico<T extends Comparable<T>> implements TNodoGenerico<T> {

    private final T dato;
    private NodoGenerico<T> primerHijo;
    private NodoGenerico<T> hermanoDerecho;

    public NodoGenerico(T dato) {
        this.dato = dato;
        this.primerHijo = null;
        this.hermanoDerecho = null;
    }

    @Override
    public T getDato() {
        return dato;
    }

    /**
     * Agrega hijo como último hijo de padre. Devuelve false si no existe padre o si hijo ya está en el árbol.
     */
    @Override
    public boolean agregarHijo(T padre, T hijo) {
        if (buscar(hijo) != null) {
            return false; // no se permiten datos repetidos
        }
        NodoGenerico<T> nodoPadre = buscar(padre);
        if (nodoPadre == null) {
            return false;
        }
        NodoGenerico<T> nuevo = new NodoGenerico<>(hijo);
        if (nodoPadre.primerHijo == null) {
            nodoPadre.primerHijo = nuevo;
        } else {
            NodoGenerico<T> ultimo = nodoPadre.primerHijo;
            while (ultimo.hermanoDerecho != null) {
                ultimo = ultimo.hermanoDerecho;
            }
            ultimo.hermanoDerecho = nuevo;
        }
        return true;
    }

    /**
     * Busca entre los descendientes el nodo que cumple el criterio y lo saca del árbol junto con su subárbol.
     * Devuelve el nodo eliminado, o null si no lo encontró.
     */
    @Override
    public NodoGenerico<T> eliminar(Comparable<T> criterio) {
        NodoGenerico<T> anterior = null;
        NodoGenerico<T> hijo = primerHijo;
        while (hijo != null) {
            if (criterio.compareTo(hijo.dato) == 0) {
                // se saltea al hijo en la lista de hermanos
                if (anterior == null) {
                    primerHijo = hijo.hermanoDerecho;
                } else {
                    anterior.hermanoDerecho = hijo.hermanoDerecho;
                }
                hijo.hermanoDerecho = null;
                return hijo;
            }
            NodoGenerico<T> eliminado = hijo.eliminar(criterio);
            if (eliminado != null) {
                return eliminado;
            }
            anterior = hijo;
            hijo = hijo.hermanoDerecho;
        }
        return null;
    }

    @Override
    public NodoGenerico<T> buscar(Comparable<T> criterio) {
        if (criterio.compareTo(dato) == 0) {
            return this;
        }
        for (NodoGenerico<T> hijo = primerHijo; hijo != null; hijo = hijo.hermanoDerecho) {
            NodoGenerico<T> encontrado = hijo.buscar(criterio);
            if (encontrado != null) {
                return encontrado;
            }
        }
        return null;
    }

    @Override
    public NodoGenerico<T> obtenerPadre(Comparable<T> criterio) {
        for (NodoGenerico<T> hijo = primerHijo; hijo != null; hijo = hijo.hermanoDerecho) {
            if (criterio.compareTo(hijo.dato) == 0) {
                return this;
            }
            NodoGenerico<T> padre = hijo.obtenerPadre(criterio);
            if (padre != null) {
                return padre;
            }
        }
        return null;
    }

    // primero el nodo y después los hijos
    @Override
    public void preOrden(Consumer<TNodoGenerico<T>> consumidor) {
        consumidor.accept(this);
        for (NodoGenerico<T> hijo = primerHijo; hijo != null; hijo = hijo.hermanoDerecho) {
            hijo.preOrden(consumidor);
        }
    }

    // primero el primer hijo, después el nodo y después el resto de los hijos
    @Override
    public void inOrden(Consumer<TNodoGenerico<T>> consumidor) {
        if (primerHijo != null) {
            primerHijo.inOrden(consumidor);
        }
        consumidor.accept(this);
        if (primerHijo != null) {
            for (NodoGenerico<T> hijo = primerHijo.hermanoDerecho; hijo != null; hijo = hijo.hermanoDerecho) {
                hijo.inOrden(consumidor);
            }
        }
    }

    // primero los hijos y después el nodo
    @Override
    public void postOrden(Consumer<TNodoGenerico<T>> consumidor) {
        for (NodoGenerico<T> hijo = primerHijo; hijo != null; hijo = hijo.hermanoDerecho) {
            hijo.postOrden(consumidor);
        }
        consumidor.accept(this);
    }

    /**
     * Una hoja tiene altura 0.
     */
    @Override
    public int altura() {
        int mayor = -1;
        for (NodoGenerico<T> hijo = primerHijo; hijo != null; hijo = hijo.hermanoDerecho) {
            mayor = Math.max(mayor, hijo.altura());
        }
        return mayor + 1;
    }

    @Override
    public int grado() {
        int cantidad = 0;
        for (NodoGenerico<T> hijo = primerHijo; hijo != null; hijo = hijo.hermanoDerecho) {
            cantidad++;
        }
        return cantidad;
    }

    @Override
    public void vaciar() {
        primerHijo = null;
    }

    @Override
    public List<T> obtenerHijos() {
        List<T> hijos = new ArrayList<>();
        for (NodoGenerico<T> hijo = primerHijo; hijo != null; hijo = hijo.hermanoDerecho) {
            hijos.add(hijo.dato);
        }
        return hijos;
    }
}
