package ucu.edu.aed.medible.medibles;

import ucu.edu.aed.medible.lib.Medible;
import ucu.edu.aed.tda.trie.TTrie;

/**
 * Mide cuánto tarda un trie (Trie o TTrieHashMap) en encontrar las palabras que empiezan con un prefijo.
 */
public class MedicionPredecirTrie extends Medible<String> {

    private final TTrie<String> trie;

    public MedicionPredecirTrie(TTrie<String> trie) {
        this.trie = trie;
    }

    @Override
    public void ejecutar(int repeticiones, String prefijo) {
        for (int i = 0; i < repeticiones; i++) {
            trie.predecir(prefijo); // baja hasta el prefijo y junta las palabras de ese subárbol
        }
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        return this.trie;
    }
}