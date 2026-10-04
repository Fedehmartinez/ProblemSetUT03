package ucu.edu.aed.Ejercicio12;

import ucu.edu.aed.tda.trie.Entry;
import ucu.edu.aed.tda.trie.impl.TTrieHashMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Trie con todos los sufijos de un texto. El dato de cada sufijo es la posición donde empieza.
 */
public class ArbolSufijos {

    private final TTrieHashMap<Integer> trie;
    private final String texto;

    public ArbolSufijos(String texto) {
        this.texto = texto;
        this.trie = new TTrieHashMap<>();
        int i = 0;
        while (i < texto.length()) {
            trie.insertar(texto.substring(i), i); // sufijo que empieza en i
            i++;
        }
    }

    /**
     * Devuelve las posiciones del texto donde empieza el patrón, ordenadas.
     * Si no aparece (o el patrón es vacío) devuelve una lista vacía.
     */
    public List<Integer> buscarPatron(String patron) {
        List<Integer> posiciones = new ArrayList<>();
        if (patron.isEmpty()) {
            return posiciones;
        }
        // los sufijos que empiezan con el patrón son justo las posiciones donde aparece
        for (Entry<Integer> e : trie.predecir(patron)) {
            posiciones.add(e.getDato());
        }
        Collections.sort(posiciones); // el HashMap no las devuelve en orden
        return posiciones;
    }

    public String getTexto() {
        return texto;
    }
}
