package ucu.edu.aed.tda.generic_trie.impl;

import ucu.edu.aed.tda.generic_trie.TArbolGenerico;

import java.util.function.Consumer;

/**
 * Árbol genérico representado con primer hijo y hermano derecho.
 */
public class ArbolGenerico<T extends Comparable<T>> implements TArbolGenerico<T> {

    private NodoGenerico<T> raiz;

    public ArbolGenerico(T datoRaiz) {
        this.raiz = new NodoGenerico<>(datoRaiz);
    }

    @Override
    public boolean agregarHijo(Comparable<T> padre, T hijo) {
        T datoPadre = buscar(padre);
        if (datoPadre == null) {
            return false;
        }
        return raiz.agregarHijo(datoPadre, hijo);
    }

    @Override
    public void eliminar(Comparable<T> criterio) {
        if (raiz == null) {
            return;
        }
        if (criterio.compareTo(raiz.getDato()) == 0) {
            raiz = null; // si se elimina la raíz, el árbol queda vacío
        } else {
            raiz.eliminar(criterio);
        }
    }

    @Override
    public T obtenerPadre(Comparable<T> criterio) {
        if (raiz == null) {
            return null;
        }
        NodoGenerico<T> padre = raiz.obtenerPadre(criterio);
        return padre == null ? null : padre.getDato();
    }

    @Override
    public T buscar(Comparable<T> criterio) {
        NodoGenerico<T> nodo = buscarNodo(criterio);
        return nodo == null ? null : nodo.getDato();
    }

    private NodoGenerico<T> buscarNodo(Comparable<T> criterio) {
        if (raiz == null) {
            return null;
        }
        return raiz.buscar(criterio);
    }

    // los recorridos del nodo pasan nodos, acá se le pasa al consumidor solo el dato
    @Override
    public void preOrden(Consumer<T> consumidor) {
        if (raiz != null) {
            raiz.preOrden(nodo -> consumidor.accept(nodo.getDato()));
        }
    }

    @Override
    public void inOrden(Consumer<T> consumidor) {
        if (raiz != null) {
            raiz.inOrden(nodo -> consumidor.accept(nodo.getDato()));
        }
    }

    @Override
    public void postOrden(Consumer<T> consumidor) {
        if (raiz != null) {
            raiz.postOrden(nodo -> consumidor.accept(nodo.getDato()));
        }
    }

    @Override
    public void vaciar() {
        raiz = null;
    }

    /**
     * Devuelve -1 si el nodo no existe.
     */
    @Override
    public int grado(Comparable<T> nodo) {
        NodoGenerico<T> encontrado = buscarNodo(nodo);
        return encontrado == null ? -1 : encontrado.grado();
    }

    /**
     * Devuelve -1 si el nodo no existe.
     */
    @Override
    public int altura(Comparable<T> nodo) {
        NodoGenerico<T> encontrado = buscarNodo(nodo);
        return encontrado == null ? -1 : encontrado.altura();
    }
}
