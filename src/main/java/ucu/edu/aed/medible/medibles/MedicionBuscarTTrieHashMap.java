package ucu.edu.aed.medible.medibles;

import ucu.edu.aed.medible.lib.Medible;
import ucu.edu.aed.tda.trie.impl.TTrieHashMap;

import java.util.List;

/**
 * Mide cuánto tarda el trie en buscar una lista de palabras.
 */
public class MedicionBuscarTTrieHashMap extends Medible<List<String>> {

    private final TTrieHashMap<String> trie;

    public MedicionBuscarTTrieHashMap(TTrieHashMap<String> trie) {
        this.trie = trie;
    }

    @Override
    public void ejecutar(int repeticiones, List<String> palabras) {
        for (int i = 0; i < repeticiones; i++) {
            for (String palabra : palabras) {
                trie.buscar(palabra);
            }
        }
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        return this.trie;
    }
}