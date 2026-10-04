package ucu.edu.aed.medible;


import ucu.edu.aed.medible.lib.Medible;
import ucu.edu.aed.medible.lib.Medicion;
import ucu.edu.aed.medible.medibles.MedicionBuscarArrayList;
import ucu.edu.aed.medible.medibles.MedicionBuscarHashMap;
import ucu.edu.aed.medible.medibles.MedicionBuscarLinkedList;
import ucu.edu.aed.medible.medibles.MedicionBuscarTreeMap;
import ucu.edu.aed.medible.medibles.MedicionBuscarTrie;
import ucu.edu.aed.medible.medibles.MedicionPredecirHashMap;
import ucu.edu.aed.medible.medibles.MedicionPredecirLinkedList;
import ucu.edu.aed.medible.medibles.MedicionPredecirTrie;
import ucu.edu.aed.tda.trie.TTrie;
import ucu.edu.aed.tda.trie.impl.Trie;
import ucu.edu.aed.utils.FileUtils;

import java.util.*;

/**
 * Ejercicio 7: carga las palabras en cada estructura y mide cuánto tardan en buscar y en predecir.
 */
public class Main {

    private static final int REPETICIONES = 20;
    private static final String PREFIJO = "cas";

    public static void main(String[] args) {
        TTrie<String> trie = new Trie<>();
        LinkedList<String> linkedList = new LinkedList<>();
        ArrayList<String> arrayList = new ArrayList<>();
        Map<String, String> hashMap = new HashMap<>();
        Map<String, String> treeMap = new TreeMap<>();

        List<String> palabrasParaAgregar = new LinkedList<>();
        List<String> palabrasParaBuscar = new LinkedList<>();
        FileUtils.leerLineas("./ut03/listado-general-desordenado.txt", palabrasParaAgregar::add);
        FileUtils.leerLineas("./ut03/listado-general-palabrasBuscar.txt", palabrasParaBuscar::add);

        // parte 2: las mismas palabras en las 5 estructuras (la palabra es la clave y también el dato)
        for (String p : palabrasParaAgregar) {
            trie.insertar(p, p);
            linkedList.add(p);
            arrayList.add(p);
            hashMap.put(p, p);
            treeMap.put(p, p);
        }

        // parte 3: buscar las palabras de palabrasBuscar
        List<Medible<List<String>>> medibles = new LinkedList<>();
        medibles.add(new MedicionBuscarLinkedList(linkedList));
        medibles.add(new MedicionBuscarArrayList(arrayList));
        medibles.add(new MedicionBuscarTrie(trie));
        medibles.add(new MedicionBuscarHashMap(hashMap));
        medibles.add(new MedicionBuscarTreeMap(treeMap));

        // parte 5: predecir las palabras que empiezan con el prefijo
        List<Medible<String>> mediblesPredecir = new LinkedList<>();
        mediblesPredecir.add(new MedicionPredecirLinkedList(linkedList));
        mediblesPredecir.add(new MedicionPredecirTrie(trie));
        mediblesPredecir.add(new MedicionPredecirHashMap(hashMap));

        StringBuilder sb = new StringBuilder();
        sb.append("algoritmo;memoria;tiempo\n");

        for (Medible<List<String>> m : medibles) {
            Medicion mi = m.medir(REPETICIONES, palabrasParaBuscar);
            mi.print();
            sb
                    .append(mi.toCSV())
                    .append("\n");
        }

        for (Medible<String> m : mediblesPredecir) {
            Medicion mi = m.medir(REPETICIONES, PREFIJO);
            mi.print();
            sb
                    .append(mi.toCSV())
                    .append("\n");
        }

        FileUtils.escribirLineas("./salida.csv", sb.toString());
    }
}
