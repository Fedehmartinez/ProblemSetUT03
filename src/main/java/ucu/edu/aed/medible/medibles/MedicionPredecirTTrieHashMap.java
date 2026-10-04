package ucu.edu.aed.medible.medibles;

import ucu.edu.aed.medible.lib.Medible;
import ucu.edu.aed.tda.trie.impl.TTrieHashMap;

public class MedicionPredecirTTrieHashMap extends Medible<String> {

    private final TTrieHashMap<String> trie;

    public MedicionPredecirTTrieHashMap(TTrieHashMap<String> trie) {
        this.trie = trie;
    }

    @Override
    public void ejecutar(int repeticiones, String prefijo) {
        for (int i = 0; i < repeticiones; i++) {
            trie.predecir(prefijo);
        }
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        return this.trie;
    }
}