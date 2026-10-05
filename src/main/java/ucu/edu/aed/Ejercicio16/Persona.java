package ucu.edu.aed.Ejercicio16;

public class Persona implements Comparable<Persona> {
  private final String nombre;
  private final int anioNacimiento;

  public Persona(String nombre, int anioNacimiento) {
    this.nombre = nombre;
    this.anioNacimiento = anioNacimiento;
  }

  public String getNombre() { return nombre; }

  public int getAnioNacimiento() { return anioNacimiento; }

  @Override
  public int compareTo(Persona otra) {
    return nombre.compareTo(otra.nombre);
  }

  @Override
  public String toString() {
    return nombre + " (" + anioNacimiento + ")";
  }
}