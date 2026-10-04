package ucu.edu.aed.medible.medibles;

import ucu.edu.aed.medible.lib.Medible;

import java.util.HashMap;
import java.util.List;

/**
 * Mide cuánto tarda el HashMap en buscar una lista de palabras.
 */
public class MedicionBuscarHashMap extends Medible<List<String>> {

    private final HashMap<String, String> map;

    public MedicionBuscarHashMap(HashMap<String, String> map) {
        this.map = map;
    }

    @Override
    public void ejecutar(int repeticiones, List<String> palabras) {
        for (int i = 0; i < repeticiones; i++) {
            for (String palabra : palabras) {
                //noinspection ResultOfMethodCallIgnored
                map.containsKey(palabra);
            }
        }
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        return this.map;
    }
}