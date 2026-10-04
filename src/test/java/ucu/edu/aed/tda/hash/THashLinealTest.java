package ucu.edu.aed.tda.hash;

import ucu.edu.aed.tda.hash.impl.THashLineal;

/**
 * Corre los tests de la plantilla sobre THashLineal.
 */
public class THashLinealTest extends AbstractTHashTest {

    @Override
    protected <K, V> THash<K, V> crearHash(int elementosEsperados) {
        return new THashLineal<>(elementosEsperados);
    }

    // después de borrar, las claves que estaban más adelante en el sondeo se tienen que seguir encontrando
    public void testBorrarNoCortaElCamino() {
        THash<Integer, String> hash = crearHash(10); // tamaño 17
        hash.insertar(1, "uno");
        hash.insertar(18, "dieciocho"); // 18 mod 17 = 1, colisiona con el 1
        hash.insertar(35, "treinta y cinco"); // 35 mod 17 = 1, también

        assertTrue(hash.delete(18));

        assertEquals("treinta y cinco", hash.buscar(35));
        assertTrue(hash.insertar(18, "otra vez"));
        assertEquals("otra vez", hash.buscar(18));
    }

    public void testReportCuentaComparaciones() {
        THash<Integer, String> hash = crearHash(10);
        hash.insertar(1, "uno");
        hash.insertar(18, "dieciocho");

        Report report = new Report();
        hash.buscar(18, report);
        assertEquals(2, report.getCantidadComparaciones()); // mira la posición del 1 y después la del 18
    }
}
