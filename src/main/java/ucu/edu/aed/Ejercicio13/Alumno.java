package ucu.edu.aed.Ejercicio13;

import java.util.Objects;

public class Alumno {
    private int id;
    private String nombreCompleto;
    private String correo;

    public Alumno(int id, String nombreCompleto, String correo) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
    }

    // Dos alumnos son iguales si tienen el mismo id, nombre y correo
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Alumno otro = (Alumno) o;
        return id == otro.id
                && Objects.equals(nombreCompleto, otro.nombreCompleto)
                && Objects.equals(correo, otro.correo);
    }

    // Usa los mismos campos que equals
    @Override
    public int hashCode() {
        return Objects.hash(id, nombreCompleto, correo);
    }
}
