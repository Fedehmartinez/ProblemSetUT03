package ucu.edu.aed.medible.medibles;

import ucu.edu.aed.medible.lib.Medible;
import ucu.edu.aed.tda.trie.TTrie;

import java.util.List;

/**
 * Mide cuánto tarda un trie (Trie o TTrieHashMap) en buscar una lista de palabras.
 */
public class MedicionBuscarTrie extends Medible<List<String>> {

    private final TTrie<String> trie;

    public MedicionBuscarTrie(TTrie<String> trie) {
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