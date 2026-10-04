package ucu.edu.aed.tda.generic_trie;

import ucu.edu.aed.tda.generic_trie.impl.ArbolGenerico;

/**
 * Corre los tests de la plantilla sobre ArbolGenerico.
 */
public class ArbolGenericoTest extends AbstractTArbolGenericoTest {

    @Override
    protected <T extends Comparable<T>> TArbolGenerico<T> crearArbol(T raiz) {
        return new ArbolGenerico<>(raiz);
    }

    public void testNodoQueNoExisteDevuelveMenosUno() {
        TArbolGenerico<Integer> arbol = crearArbol(1);
        assertEquals(-1, arbol.grado(99));
        assertEquals(-1, arbol.altura(99));
    }
}
