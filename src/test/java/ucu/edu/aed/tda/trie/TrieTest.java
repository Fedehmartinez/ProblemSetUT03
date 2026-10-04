package ucu.edu.aed.tda.trie;

import junit.framework.TestCase;
import ucu.edu.aed.tda.trie.impl.Trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrieTest extends TestCase {

    private Trie<Integer> trie;

    protected void setUp() {
        trie = new Trie<>();
        trie.insertar("sol", 42);
        trie.insertar("so", 7);
        trie.insertar("sal", 99);
        trie.insertar("mal", 50);
        trie.insertar("ma", 1);
    }

    public void testTrieNuevoEstaVacio() {
        Trie<Integer> vacio = new Trie<>();
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

    // con el arreglo las palabras ya salen en orden alfabético, no hace falta ordenarlas
    public void testPredecirEnOrdenAlfabetico() {
        assertEquals(Arrays.asList("sal", "so", "sol"), palabras(trie.predecir("s")));
        assertEquals(Arrays.asList("ma", "mal"), palabras(trie.predecir("m")));
        assertEquals(Arrays.asList("sol"), palabras(trie.predecir("sol")));
        assertEquals(Arrays.asList("ma", "mal", "sal", "so", "sol"), palabras(trie.predecir("")));
    }

    public void testPredecirPrefijoQueNoExiste() {
        assertTrue(trie.predecir("x").isEmpty());
        assertTrue(trie.predecir("solcito").isEmpty());
    }

    public void testRecorrer() {
        List<String> recorridas = new ArrayList<>();
        trie.recorrer(e -> recorridas.add(e.getPalabra()));
        assertEquals(Arrays.asList("ma", "mal", "sal", "so", "sol"), recorridas);
    }

    // el arreglo solo tiene lugar para la a..z
    public void testCaracteresFueraDeLaAZNoSeInsertan() {
        assertFalse(trie.insertar("ñandú", 10));
        assertFalse(trie.insertar("Sol", 11));
        assertFalse(trie.insertar("so2", 12));
        assertNull(trie.buscar("ñandú"));
        assertNull(trie.buscar("Sol"));
        assertEquals(Arrays.asList("so", "sol"), palabras(trie.predecir("so"))); // no quedó nada a medias
    }

    private static List<String> palabras(List<Entry<Integer>> entries) {
        List<String> res = new ArrayList<>();
        for (Entry<Integer> e : entries) {
            res.add(e.getPalabra());
        }
        return res;
    }
}
