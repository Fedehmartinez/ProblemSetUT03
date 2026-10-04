package ucu.edu.aed.tda.hash.impl;

import ucu.edu.aed.tda.hash.Entry;
import ucu.edu.aed.tda.hash.Report;
import ucu.edu.aed.tda.hash.THash;
import ucu.edu.aed.tda.hash.TNodoHash;

import java.util.ArrayList;
import java.util.List;

/**
 * Tabla hash con direccionamiento abierto y sondeo lineal: h(i) = (h(0) + i) mod M.
 * Al eliminar, el nodo no se borra: se marca como lote libre para no cortar el camino de otras claves.
 */
public class THashLineal<K, V> extends THash<K, V> {

    // por encima de este factor de carga el sondeo lineal se pone lento (ver Ej. 9)
    private static final double FACTOR_CARGA_MAX = 0.7;

    private int cantidad;

    public THashLineal(int elementosEsperados) {
        super(elementosEsperados);
        this.cantidad = 0;
    }

    @Override
    public V buscar(K clave, Report report) {
        int pos = buscarPosicion(clave, report);
        if (pos == -1) {
            return null;
        }
        return hashTable[pos].getValor();
    }

    @Override
    public boolean delete(K clave, Report report) {
        int pos = buscarPosicion(clave, report);
        if (pos == -1) {
            return false;
        }
        hashTable[pos].setLoteLibre(true);
        cantidad--;
        return true;
    }

    @Override
    public boolean insertar(K clave, V valor, Report report) {
        if (cantidad + 1 > hashTable.length * FACTOR_CARGA_MAX) {
            redimensionar();
        }
        int pos = functionHashing(clave);
        int libre = -1; // primer lote libre que se encuentra, ahí se puede insertar
        int comparaciones = 0;
        while (comparaciones < hashTable.length) {
            comparaciones++;
            TNodoHash<K, V> nodo = hashTable[pos];
            if (nodo == null) {
                break; // posición vacía, la clave no está
            }
            if (nodo.isLoteLibre()) {
                if (libre == -1) {
                    libre = pos;
                }
            } else if (nodo.getClave().equals(clave)) {
                report.setCantidadComparaciones(comparaciones);
                return false; // la clave ya estaba
            }
            pos = (pos + 1) % hashTable.length;
        }
        if (libre == -1) {
            libre = pos;
        }
        hashTable[libre] = new TNodoHash<>(clave, valor);
        cantidad++;
        report.setCantidadComparaciones(comparaciones);
        return true;
    }

    // devuelve la posición de la clave en la tabla, o -1 si no está
    private int buscarPosicion(K clave, Report report) {
        int pos = functionHashing(clave);
        int comparaciones = 0;
        while (comparaciones < hashTable.length) {
            comparaciones++;
            TNodoHash<K, V> nodo = hashTable[pos];
            if (nodo == null) {
                break; // posición vacía, la clave no está
            }
            if (!nodo.isLoteLibre() && nodo.getClave().equals(clave)) {
                report.setCantidadComparaciones(comparaciones);
                return pos;
            }
            pos = (pos + 1) % hashTable.length;
        }
        report.setCantidadComparaciones(comparaciones);
        return -1;
    }

    @Override
    protected int functionHashing(K clave) {
        return Math.abs(clave.hashCode() % hashTable.length);
    }

    @Override
    public boolean esVacio() {
        return cantidad == 0;
    }

    @Override
    public void vaciar() {
        for (int i = 0; i < hashTable.length; i++) {
            hashTable[i] = null;
        }
        cantidad = 0;
    }

    @Override
    protected int calcularCapacidadOptima(int elementosEsperados) {
        int capacidad = (int) Math.ceil(elementosEsperados / FACTOR_CARGA_MAX);
        return siguientePrimo(Math.max(capacidad, 2)); // un tamaño primo reparte mejor las claves
    }

    @Override
    @SuppressWarnings("unchecked")
    protected boolean redimensionar() {
        TNodoHash<K, V>[] vieja = hashTable;
        hashTable = new TNodoHash[siguientePrimo(vieja.length * 2)];
        // se vuelven a ubicar las claves, porque con otro tamaño cambia la función hash
        for (TNodoHash<K, V> nodo : vieja) {
            if (nodo != null && !nodo.isLoteLibre()) {
                int pos = functionHashing(nodo.getClave());
                while (hashTable[pos] != null) {
                    pos = (pos + 1) % hashTable.length;
                }
                hashTable[pos] = nodo;
            }
        }
        return true;
    }

    @Override
    public Iterable<Entry<K, V>> entries() {
        List<Entry<K, V>> resultado = new ArrayList<>();
        for (TNodoHash<K, V> nodo : hashTable) {
            if (nodo != null && !nodo.isLoteLibre()) {
                resultado.add(nodo.getEntry());
            }
        }
        return resultado;
    }

    @Override
    public Iterable<K> keys() {
        List<K> resultado = new ArrayList<>();
        for (Entry<K, V> e : entries()) {
            resultado.add(e.getClave());
        }
        return resultado;
    }

    @Override
    public Iterable<V> values() {
        List<V> resultado = new ArrayList<>();
        for (Entry<K, V> e : entries()) {
            resultado.add(e.getValor());
        }
        return resultado;
    }

    private static int siguientePrimo(int n) {
        while (!esPrimo(n)) {
            n++;
        }
        return n;
    }

    private static boolean esPrimo(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
