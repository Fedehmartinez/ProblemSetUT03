package ucu.edu.aed.Ejercicio16;

import ucu.edu.aed.tda.generic_trie.TArbolGenerico;
import ucu.edu.aed.tda.generic_trie.impl.ArbolGenerico;

import java.util.ArrayList;
import java.util.List;

public class ArbolGenealogico {
  private final TArbolGenerico<Persona> arbol;
  private final Persona raiz;

  public ArbolGenealogico(Persona raiz) {
    this.arbol = new ArbolGenerico<>(raiz);
    this.raiz = raiz;
  }

  public boolean agregarHijo(Persona padre, Persona hijo) {
    return arbol.agregarHijo(padre, hijo);
  }

  private List<Persona> camino(Persona persona) {
    List<Persona> camino = new ArrayList<>();
    Persona actual = arbol.buscar(persona);
    while (actual != null) {
      camino.add(actual);
      actual = arbol.obtenerPadre(actual);
    }
    return camino;
  }

  private List<Persona> todas() {
    List<Persona> todas = new ArrayList<>();
    arbol.preOrden(todas::add);
    return todas;
  }

  public boolean esDescendiente(Persona persona, Persona ancestro) {
    List<Persona> camino = camino(persona);
    for (int i = 1; i < camino.size(); i++) {
      if (camino.get(i).compareTo(ancestro) == 0) {
        return true;
      }
    }
    return false;
  }

  public List<Persona> listarDescendientes(Persona persona) {
    List<Persona> descendientes = new ArrayList<>();
    for (Persona p : todas()) {
      if (esDescendiente(p, persona)) {
        descendientes.add(p);
      }
    }
    return descendientes;
  }

  public int altura() {
    return arbol.altura(raiz);
  }

  public int contarPersonas() {
    return todas().size();
  }

  public List<Persona> personasDeGeneracion(int generacion) {
    List<Persona> resultado = new ArrayList<>();
    for (Persona p : todas()) {
      if (camino(p).size() - 1 == generacion) {
        resultado.add(p);
      }
    }
    return resultado;
  }

  public Persona ancestroComun(Persona a, Persona b) {
    List<Persona> caminoB = camino(b);
    for (Persona ancestro : camino(a)) {
      for (Persona p : caminoB) {
        if (ancestro.compareTo(p) == 0) {
          return ancestro;
        }
      }
    }
    return null;
  }
}