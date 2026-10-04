package ucu.edu.aed.Ejercicio12;

import junit.framework.TestCase;

import java.util.Arrays;

public class ArbolSufijosTest extends TestCase {

    private final ArbolSufijos banana = new ArbolSufijos("banana");

    public void testPatronQueApareceVariasVeces() {
        assertEquals(Arrays.asList(1, 3), banana.buscarPatron("ana"));
        assertEquals(Arrays.asList(2, 4), banana.buscarPatron("na"));
        assertEquals(Arrays.asList(1, 3, 5), banana.buscarPatron("a"));
    }

    public void testPatronEsTodoElTexto() {
        assertEquals(Arrays.asList(0), banana.buscarPatron("banana"));
    }

    public void testPatronQueNoAparece() {
        assertTrue(banana.buscarPatron("nab").isEmpty());
        assertTrue(banana.buscarPatron("x").isEmpty());
        assertTrue(banana.buscarPatron("bananas").isEmpty());
    }

    public void testPatronVacio() {
        assertTrue(banana.buscarPatron("").isEmpty());
    }

    public void testPatronesSolapados() {
        ArbolSufijos a = new ArbolSufijos("aaaa");
        assertEquals(Arrays.asList(0, 1, 2), a.buscarPatron("aa"));
        assertEquals(Arrays.asList(0, 1, 2, 3), a.buscarPatron("a"));
    }

    public void testTextoConEspacios() {
        ArbolSufijos a = new ArbolSufijos("la casa de la playa");
        assertEquals(Arrays.asList(0, 11), a.buscarPatron("la "));
        assertEquals(Arrays.asList(0, 11, 15), a.buscarPatron("la"));
    }
}
