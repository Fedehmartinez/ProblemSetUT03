package ucu.edu.aed.medible.medibles;

import ucu.edu.aed.medible.lib.Medible;

import java.util.LinkedList;
import java.util.List;

/**
 * Mide cuánto tarda la LinkedList en encontrar las palabras que empiezan con un prefijo.
 */
public class MedicionPredecirLinkedList extends Medible<String> {

    private final LinkedList<String> list;

    public MedicionPredecirLinkedList(LinkedList<String> list) {
        this.list = list;
    }

    @Override
    public void ejecutar(int repeticiones, String prefijo) {
        for (int i = 0; i < repeticiones; i++) {
            // se guardan las coincidencias, igual que hace trie.predecir()
            List<String> resultado = new LinkedList<>();
            for (String palabra : list) {
                if (palabra.startsWith(prefijo)) {
                    resultado.add(palabra);
                }
            }
        }
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        return this.list;
    }
}