package ucu.edu.aed.medible.medibles;

import ucu.edu.aed.medible.lib.Medible;

import java.util.LinkedList;

public class MedicionPredecirLinkedList extends Medible<String> {

    private final LinkedList<String> list;

    public MedicionPredecirLinkedList(LinkedList<String> list) {
        this.list = list;
    }

    @Override
    public void ejecutar(int repeticiones, String prefijo) {
        for (int i = 0; i < repeticiones; i++) {
            for (String palabra : list) {
                palabra.startsWith(prefijo);
            }
        }
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        return this.list;
    }
}