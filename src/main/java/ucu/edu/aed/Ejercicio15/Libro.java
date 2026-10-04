package ucu.edu.aed.Ejercicio15;

import java.util.Objects;

public class Libro {
 
    private final String isbn;
    private String titulo;
    private String autor;
    private int anio;
 
    public Libro(String isbn, String titulo, String autor, int anio) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
    }
 
    public String getIsbn() {
        return isbn;
    }
 
    public String getTitulo() {
        return titulo;
    }
 
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
 
    public String getAutor() {
        return autor;
    }
 
    public void setAutor(String autor) {
        this.autor = autor;
    }
 
    public int getAnio() {
        return anio;
    }
 
    public void setAnio(int anio) {
        this.anio = anio;
    }

    @Override 
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Libro otro = (Libro) o;
        return Objects.equals(isbn, otro.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(isbn);
    }
}