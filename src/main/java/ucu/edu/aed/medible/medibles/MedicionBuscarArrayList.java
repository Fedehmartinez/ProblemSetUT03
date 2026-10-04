package ucu.edu.aed.medible.medibles;

import ucu.edu.aed.medible.lib.Medible;

import java.util.ArrayList;
import java.util.List;

public class MedicionBuscarArrayList extends Medible<List<String>> {

    private final ArrayList<String> list;

    public MedicionBuscarArrayList(ArrayList<String> list) {
        this.list = list;
    }

    @Override
    public void ejecutar(int repeticiones, List<String> palabras) {
        for (int i = 0; i < repeticiones; i++) {
            for (String palabra : palabras) {
                //noinspection ResultOfMethodCallIgnored
                list.contains(palabra);
            }
        }
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        return this.list;
    }
}