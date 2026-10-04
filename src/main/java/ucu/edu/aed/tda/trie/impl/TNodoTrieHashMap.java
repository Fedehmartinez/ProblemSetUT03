package ucu.edu.aed.tda.trie.impl;

import ucu.edu.aed.tda.trie.Entry;
import ucu.edu.aed.tda.trie.TNodoTrie;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class TNodoTrieHashMap<T> implements TNodoTrie<T> {

    // la letra de cada hijo es la clave del map
    private final Map<Character, TNodoTrieHashMap<T>> hijos;
    private boolean esPalabra;
    private T dato;

    public TNodoTrieHashMap() {
        this.hijos = new HashMap<>();
        this.esPalabra = false;
        this.dato = null;
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
        TNodoTrieHashMap<T> nodo = buscarNodo(palabra);
        if (nodo == null) {
            return null;
        }
        return new Entry<>(nodo.dato, nodo.esPalabra, palabra);
    }

    private TNodoTrieHashMap<T> buscarNodo(String palabra) {
        if (palabra.isEmpty()) {
            return this; // llegué al nodo buscado
        }
        char letra = palabra.charAt(0);
        TNodoTrieHashMap<T> hijo = hijos.get(letra);
        if (hijo == null) {
            return null; // se cortó el camino
        }
        return hijo.buscarNodo(palabra.substring(1));
    }

    @Override
    public boolean insertar(String palabra, T unDato) {
        if (palabra.isEmpty()) {
            if (esPalabra) {
                return false; // ya estaba insertada
            }
            esPalabra = true;
            dato = unDato;
            return true;
        }
        char letra = palabra.charAt(0);
        TNodoTrieHashMap<T> hijo = hijos.get(letra);
        if (hijo == null) { // si no existe el camino, lo creo
            hijo = new TNodoTrieHashMap<>();
            hijos.put(letra, hijo);
        }
        return hijo.insertar(palabra.substring(1), unDato);
    }

    @Override
    public List<Entry<T>> predecir(String prefijo) {
        List<Entry<T>> resultado = new LinkedList<>();
        TNodoTrieHashMap<T> nodo = buscarNodo(prefijo);
        if (nodo != null) {
            nodo.recolectar(prefijo, resultado);
        }
        return resultado;
    }

    private void recolectar(String palabraArmada, List<Entry<T>> resultado) {
        if (esPalabra) {
            resultado.add(new Entry<>(dato, true, palabraArmada));
        }
        // Map.Entry es el par (letra, hijo) del map, no la Entry del trie
        for (Map.Entry<Character, TNodoTrieHashMap<T>> e : hijos.entrySet()) {
            char letra = e.getKey();
            TNodoTrieHashMap<T> hijo = e.getValue();
            hijo.recolectar(palabraArmada + letra, resultado); // le pego la letra a la palabra
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
