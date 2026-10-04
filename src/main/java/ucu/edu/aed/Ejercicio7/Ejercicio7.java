package ucu.edu.aed.Ejercicio7;

import ucu.edu.aed.medible.medibles.MedicionBuscarArrayList;
import ucu.edu.aed.medible.medibles.MedicionBuscarHashMap;
import ucu.edu.aed.medible.medibles.MedicionBuscarLinkedList;
import ucu.edu.aed.medible.medibles.MedicionBuscarTTrieHashMap;
import ucu.edu.aed.medible.medibles.MedicionBuscarTreeMap;
import ucu.edu.aed.tda.trie.impl.TTrieHashMap;
import ucu.edu.aed.medible.medibles.MedicionPredecirHashMap;
import ucu.edu.aed.medible.medibles.MedicionPredecirLinkedList;
import ucu.edu.aed.medible.medibles.MedicionPredecirTTrieHashMap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class Ejercicio7 {

    public static void main(String[] args) throws IOException {

        String archivoPalabras =
                "src/main/resources/ut03/listado-general-desordenado.txt";

        String archivoBuscar =
                "src/main/resources/ut03/listado-general-palabrasBuscar.txt";

        LinkedList<String> linkedList = cargarLinkedList(archivoPalabras);
        ArrayList<String> arrayList = cargarArrayList(archivoPalabras);
        HashMap<String, String> hashMap = cargarHashMap(archivoPalabras);
        TreeMap<String, String> treeMap = cargarTreeMap(archivoPalabras);
        TTrieHashMap<String> trie = cargarTrie(archivoPalabras);

        List<String> palabrasBuscar = cargarPalabrasBuscar(archivoBuscar);

        MedicionBuscarLinkedList medicionLinkedList =
                new MedicionBuscarLinkedList(linkedList);

        MedicionBuscarArrayList medicionArrayList =
                new MedicionBuscarArrayList(arrayList);

        MedicionBuscarHashMap medicionHashMap =
                new MedicionBuscarHashMap(hashMap);

        MedicionBuscarTreeMap medicionTreeMap =
                new MedicionBuscarTreeMap(treeMap);

        MedicionBuscarTTrieHashMap medicionTrie =
                new MedicionBuscarTTrieHashMap(trie);

        MedicionPredecirLinkedList medicionPredecirLinkedList =
                new MedicionPredecirLinkedList(linkedList);

        MedicionPredecirHashMap medicionPredecirHashMap =
                new MedicionPredecirHashMap(hashMap);

        MedicionPredecirTTrieHashMap medicionPredecirTrie =
                new MedicionPredecirTTrieHashMap(trie);

        medicionLinkedList.medir(20, palabrasBuscar).print();
        medicionArrayList.medir(20, palabrasBuscar).print();
        medicionHashMap.medir(20, palabrasBuscar).print();
        medicionTreeMap.medir(20, palabrasBuscar).print();
        medicionTrie.medir(20, palabrasBuscar).print();

        medicionPredecirLinkedList.medir(20, "cas").print();
        medicionPredecirHashMap.medir(20, "cas").print();
        medicionPredecirTrie.medir(20, "cas").print();
    }

    public static LinkedList<String> cargarLinkedList(String archivo)
            throws IOException {

        LinkedList<String> lista = new LinkedList<>();

        BufferedReader lector = new BufferedReader(new FileReader(archivo));

        String palabra;

        while ((palabra = lector.readLine()) != null) {
            lista.add(palabra);
        }

        lector.close();

        return lista;
    }

    public static ArrayList<String> cargarArrayList(String archivo)
            throws IOException {

        ArrayList<String> lista = new ArrayList<>();

        BufferedReader lector = new BufferedReader(new FileReader(archivo));

        String palabra;

        while ((palabra = lector.readLine()) != null) {
            lista.add(palabra);
        }

        lector.close();

        return lista;
    }

    public static HashMap<String, String> cargarHashMap(String archivo)
            throws IOException {

        HashMap<String, String> mapa = new HashMap<>();

        BufferedReader lector = new BufferedReader(new FileReader(archivo));

        String palabra;

        while ((palabra = lector.readLine()) != null) {
            mapa.put(palabra, palabra);
        }

        lector.close();

        return mapa;
    }

    public static TreeMap<String, String> cargarTreeMap(String archivo)
            throws IOException {

        TreeMap<String, String> mapa = new TreeMap<>();

        BufferedReader lector = new BufferedReader(new FileReader(archivo));

        String palabra;

        while ((palabra = lector.readLine()) != null) {
            mapa.put(palabra, palabra);
        }

        lector.close();

        return mapa;
    }

    public static TTrieHashMap<String> cargarTrie(String archivo)
            throws IOException {

        TTrieHashMap<String> trie = new TTrieHashMap<>();

        BufferedReader lector = new BufferedReader(new FileReader(archivo));

        String palabra;

        while ((palabra = lector.readLine()) != null) {
            trie.insertar(palabra, palabra);
        }

        lector.close();

        return trie;
    }

    public static List<String> cargarPalabrasBuscar(String archivo)
            throws IOException {

        List<String> palabras = new ArrayList<>();

        BufferedReader lector = new BufferedReader(new FileReader(archivo));

        String palabra;

        while ((palabra = lector.readLine()) != null) {
            palabras.add(palabra);
        }

        lector.close();

        return palabras;
    }
}