package ucu.edu.aed.medible.medibles;

import ucu.edu.aed.medible.lib.Medible;

import java.util.List;
import java.util.Map;

/**
 * Mide cuánto tarda el HashMap en buscar una lista de palabras.
 */
public class MedicionBuscarHashMap extends Medible<List<String>> {

    private final Map<String, String> map;

    public MedicionBuscarHashMap(Map<String, String> map) {
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