package ucu.edu.aed.tda.trie.impl;

import ucu.edu.aed.tda.trie.Entry;
import ucu.edu.aed.tda.trie.TNodoTrie;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Nodo del Trie. Los hijos están en un arreglo de 26 posiciones, una por cada letra de la a a la z.
 */
public class NodoTrie<T> implements TNodoTrie<T>, Serializable {

    private static final int CANT_LETRAS = 26;

    private final NodoTrie<T>[] hijos;
    private boolean esPalabra;
    private T dato;

    @SuppressWarnings("unchecked")
    public NodoTrie() {
        this.hijos = new NodoTrie[CANT_LETRAS];
        this.esPalabra = false;
        this.dato = null;
    }

    // posición de la letra en el arreglo ('a' -> 0, 'z' -> 25), o -1 si no es una letra de la a a la z
    private static int indice(char letra) {
        if (letra < 'a' || letra > 'z') {
            return -1;
        }
        return letra - 'a';
    }

    @Override
    public void recorrer(Consumer<Entry<T>> consumer) {
        // predecir("") devuelve todas las palabras
        for (Entry<T> e : predecir("")) {
            consumer.accept(e);
        }
    }

    @Override
    public Entry<T> buscar(String palabra) {
        NodoTrie<T> nodo = buscarNodo(palabra);
        if (nodo == null) {
            return null;
        }
        return new Entry<>(nodo.dato, nodo.esPalabra, palabra);
    }

    private NodoTrie<T> buscarNodo(String palabra) {
        NodoTrie<T> nodo = this;
        for (int i = 0; i < palabra.length(); i++) {
            int pos = indice(palabra.charAt(i));
            if (pos == -1 || nodo.hijos[pos] == null) {
                return null; // se cortó el camino
            }
            nodo = nodo.hijos[pos];
        }
        return nodo;
    }

    @Override
    public boolean insertar(String palabra, T unDato) {
        // primero se revisan las letras, así no quedan nodos creados a medias
        for (int i = 0; i < palabra.length(); i++) {
            if (indice(palabra.charAt(i)) == -1) {
                return false;
            }
        }
        NodoTrie<T> nodo = this;
        for (int i = 0; i < palabra.length(); i++) {
            int pos = indice(palabra.charAt(i));
            if (nodo.hijos[pos] == null) { // si no existe el camino, lo creo
                nodo.hijos[pos] = new NodoTrie<>();
            }
            nodo = nodo.hijos[pos];
        }
        if (nodo.esPalabra) {
            return false; // ya estaba insertada
        }
        nodo.esPalabra = true;
        nodo.dato = unDato;
        return true;
    }

    @Override
    public List<Entry<T>> predecir(String prefijo) {
        List<Entry<T>> resultado = new LinkedList<>();
        NodoTrie<T> nodo = buscarNodo(prefijo);
        if (nodo != null) {
            nodo.recolectar(prefijo, resultado);
        }
        return resultado;
    }

    private void recolectar(String palabraArmada, List<Entry<T>> resultado) {
        if (esPalabra) {
            resultado.add(new Entry<>(dato, true, palabraArmada));
        }
        // se recorre el arreglo en orden, así las palabras salen en orden alfabético
        for (int i = 0; i < CANT_LETRAS; i++) {
            if (hijos[i] != null) {
                hijos[i].recolectar(palabraArmada + (char) ('a' + i), resultado);
            }
        }
    }

    @Override
    public T getDato() {
        return dato;
    }

    @Override
    public boolean esPalabra() {
        return esPalabra;
    }
}
