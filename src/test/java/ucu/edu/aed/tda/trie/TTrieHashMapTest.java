package ucu.edu.aed.tda.trie;

import junit.framework.TestCase;
import ucu.edu.aed.tda.trie.impl.TTrieHashMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TTrieHashMapTest extends TestCase {

    private TTrieHashMap<Integer> trie;

    protected void setUp() {
        trie = new TTrieHashMap<>();
        trie.insertar("sol", 42);
        trie.insertar("so", 7);
        trie.insertar("sal", 99);
        trie.insertar("mal", 50);
        trie.insertar("ma", 1);
    }

    public void testTrieNuevoEstaVacio() {
        TTrieHashMap<Integer> vacio = new TTrieHashMap<>();
        assertNull(vacio.buscar("a"));
        assertTrue(vacio.predecir("").isEmpty());
    }

    public void testInsertarYBuscar() {
        Entry<Integer> e = trie.buscar("mal");
        assertNotNull(e);
        assertTrue(e.esPalabra());
        assertEquals(Integer.valueOf(50), e.getDato());
        assertEquals("mal", e.getPalabra());
    }

    public void testInsertarDuplicadaDevuelveFalseYNoPisaElDato() {
        assertFalse(trie.insertar("sol", 1));
        assertEquals(Integer.valueOf(42), trie.buscar("sol").getDato());
    }

    public void testPrefijoQueNoEsPalabra() {
        Entry<Integer> e = trie.buscar("sa");
        assertNotNull(e);
        assertFalse(e.esPalabra());
        assertNull(e.getDato());
    }

    public void testBuscarPalabraQueNoEsta() {
        assertNull(trie.buscar("mar"));
        assertNull(trie.buscar("x"));
        assertNull(trie.buscar("solcito"));
    }

    public void testPalabraQueEsPrefijoDeOtra() {
        assertTrue(trie.buscar("ma").esPalabra());
        assertTrue(trie.buscar("mal").esPalabra());
    }

    public void testPredecir() {
        assertEquals(List.of("sal", "so", "sol"), palabras(trie.predecir("s")));
        assertEquals(List.of("ma", "mal"), palabras(trie.predecir("m")));
        assertEquals(List.of("sol"), palabras(trie.predecir("sol")));
    }

    public void testPredecirPrefijoQueNoExiste() {
        assertTrue(trie.predecir("x").isEmpty());
        assertTrue(trie.predecir("solcito").isEmpty());
    }

    public void testPredecirVacioDevuelveTodas() {
        assertEquals(List.of("ma", "mal", "sal", "so", "sol"), palabras(trie.predecir("")));
    }

    public void testRecorrer() {
        List<String> recorridas = new ArrayList<>();
        trie.recorrer(e -> recorridas.add(e.getPalabra()));
        Collections.sort(recorridas);
        assertEquals(List.of("ma", "mal", "sal", "so", "sol"), recorridas);
    }

    // con el HashMap sirve cualquier carácter, no solo a..z
    public void testOtrosCaracteres() {
        assertTrue(trie.insertar("ñandú", 10));
        assertTrue(trie.insertar("año", 11));
        assertTrue(trie.insertar("C3PO", 12));
        assertEquals(Integer.valueOf(10), trie.buscar("ñandú").getDato());
        assertEquals(Integer.valueOf(12), trie.buscar("C3PO").getDato());
        assertEquals(List.of("ñandú"), palabras(trie.predecir("ñ")));
    }

    private static List<String> palabras(List<Entry<Integer>> entries) {
        List<String> res = new ArrayList<>();
        for (Entry<Integer> e : entries) {
            res.add(e.getPalabra());
        }
        Collections.sort(res);
        return res;
    }
}
