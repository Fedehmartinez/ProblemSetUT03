package ucu.edu.aed.Ejercicio9;

/**
 * Tabla hash de claves enteras con direccionamiento abierto lineal: h(i) = (h(0) + i) mod M.
 * insertar y buscar devuelven la cantidad de comparaciones (posiciones inspeccionadas).
 */
public class TablaHashLineal {

    private final Integer[] tabla; // null = posición libre
    private int cantidad;

    public TablaHashLineal(int tamanio) {
        this.tabla = new Integer[tamanio];
        this.cantidad = 0;
    }

    public int funcionHashing(int unaClave) {
        return Math.floorMod(unaClave, tabla.length); // h(0) = clave mod M
    }

    /**
     * Inserta la clave en la primera posición libre y devuelve las comparaciones realizadas.
     * Si la clave ya estaba no la vuelve a insertar.
     */
    public int insertar(int unaClave) {
        int pos = funcionHashing(unaClave);
        int comparaciones = 0;
        while (comparaciones < tabla.length) {
            comparaciones++;
            if (tabla[pos] == null) {
                tabla[pos] = unaClave;
                cantidad++;
                return comparaciones;
            }
            if (tabla[pos] == unaClave) {
                return comparaciones;
            }
            pos = (pos + 1) % tabla.length; // sigo con la siguiente posición
        }
        return comparaciones; // la tabla está llena
    }

    /**
     * Busca la clave y devuelve las comparaciones realizadas, la encuentre o no.
     */
    public int buscar(int unaClave) {
        int pos = funcionHashing(unaClave);
        int comparaciones = 0;
        while (comparaciones < tabla.length) {
            comparaciones++;
            if (tabla[pos] == null) {
                return comparaciones; // llegué a una libre, la clave no está
            }
            if (tabla[pos] == unaClave) {
                return comparaciones;
            }
            pos = (pos + 1) % tabla.length;
        }
        return comparaciones;
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getTamanio() {
        return tabla.length;
    }
}
