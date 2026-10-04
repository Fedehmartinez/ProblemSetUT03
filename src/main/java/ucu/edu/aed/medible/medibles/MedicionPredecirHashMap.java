package ucu.edu.aed.medible.medibles;

import ucu.edu.aed.medible.lib.Medible;

import java.util.HashMap;

public class MedicionPredecirHashMap extends Medible<String> {

    private final HashMap<String, String> map;

    public MedicionPredecirHashMap(HashMap<String, String> map) {
        this.map = map;
    }

    @Override
    public void ejecutar(int repeticiones, String prefijo) {
        for (int i = 0; i < repeticiones; i++) {
            for (String palabra : map.keySet()) {
                palabra.startsWith(prefijo);
            }
        }
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        return this.map;
    }
}