package ucu.edu.aed.tda.trie.impl;

import ucu.edu.aed.tda.trie.Entry;
import ucu.edu.aed.tda.trie.TTrie;

import java.io.Serializable;
import java.util.List;
import java.util.function.Consumer;

public class TTrieHashMap<T> implements TTrie<T>, Serializable {

    private final TNodoTrieHashMap<T> raiz;

    public TTrieHashMap() {
        this.raiz = new TNodoTrieHashMap<>();
    }

    @Override
    public void recorrer(Consumer<Entry<T>> consumer) {
        raiz.recorrer(consumer);
    }

    @Override
    public Entry<T> buscar(String palabra) {
        return raiz.buscar(palabra);
    }

    @Override
    public boolean insertar(String palabra, T dato) {
        return raiz.insertar(palabra, dato);
    }

    @Override
    public List<Entry<T>> predecir(String prefijo) {
        return raiz.predecir(prefijo);
    }
}
