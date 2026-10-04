package ucu.edu.aed.tda.trie.impl;

import ucu.edu.aed.tda.trie.Entry;
import ucu.edu.aed.tda.trie.TTrie;

import java.io.Serializable;
import java.util.List;
import java.util.function.Consumer;

/**
 * Trie para palabras con letras de la a a la z. Cada nodo guarda sus hijos en un arreglo.
 */
public class Trie<T> implements TTrie<T>, Serializable {

    private final NodoTrie<T> raiz;

    public Trie() {
        this.raiz = new NodoTrie<>();
    }

    @Override
    public void recorrer(Consumer<Entry<T>> consumer) {
        raiz.recorrer(consumer);
    }

    @Override
    public Entry<T> buscar(String palabra) {
        return raiz.buscar(palabra);
    }

    /**
     * Devuelve false si la palabra ya estaba o si tiene caracteres que no son de la a a la z.
     */
    @Override
    public boolean insertar(String palabra, T dato) {
        return raiz.insertar(palabra, dato);
    }

    @Override
    public List<Entry<T>> predecir(String prefijo) {
        return raiz.predecir(prefijo);
    }
}
