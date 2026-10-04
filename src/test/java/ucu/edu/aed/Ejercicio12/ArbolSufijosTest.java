package ucu.edu.aed.Ejercicio12;

import junit.framework.TestCase;

import java.util.List;

public class ArbolSufijosTest extends TestCase {

    private final ArbolSufijos banana = new ArbolSufijos("banana");

    public void testPatronQueApareceVariasVeces() {
        assertEquals(List.of(1, 3), banana.buscarPatron("ana"));
        assertEquals(List.of(2, 4), banana.buscarPatron("na"));
        assertEquals(List.of(1, 3, 5), banana.buscarPatron("a"));
    }

    public void testPatronEsTodoElTexto() {
        assertEquals(List.of(0), banana.buscarPatron("banana"));
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
        assertEquals(List.of(0, 1, 2), a.buscarPatron("aa"));
        assertEquals(List.of(0, 1, 2, 3), a.buscarPatron("a"));
    }

    public void testTextoConEspacios() {
        ArbolSufijos a = new ArbolSufijos("la casa de la playa");
        assertEquals(List.of(0, 11), a.buscarPatron("la "));
        assertEquals(List.of(0, 11, 15), a.buscarPatron("la"));
    }
}
