package ucu.edu.aed.medible.medibles;

import ucu.edu.aed.medible.lib.Medible;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Mide cuánto tarda el HashMap en encontrar las palabras que empiezan con un prefijo.
 */
public class MedicionPredecirHashMap extends Medible<String> {

    private final HashMap<String, String> map;

    public MedicionPredecirHashMap(HashMap<String, String> map) {
        this.map = map;
    }

    @Override
    public void ejecutar(int repeticiones, String prefijo) {
        for (int i = 0; i < repeticiones; i++) {
            // se guardan las coincidencias, igual que hace trie.predecir()
            List<String> resultado = new LinkedList<>();
            for (String palabra : map.keySet()) {
                if (palabra.startsWith(prefijo)) {
                    resultado.add(palabra);
                }
            }
        }
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        return this.map;
    }
}