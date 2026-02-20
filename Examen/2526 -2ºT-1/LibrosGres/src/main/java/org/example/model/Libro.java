package org.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "libros") // Assuming a table named 'libros' in PostgreSQL
public class Libro {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "autor")
    private String autor;
    @Column(name = "ano_publicacion")
    private int anoPublicacion;

    public Libro() {
    }

    public Libro(String id, String titulo, String autor, int anoPublicacion) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacion = anoPublicacion;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getAnoPublicacion() {
        return anoPublicacion;
    }

    public void setAnoPublicacion(int anoPublicacion) {
        this.anoPublicacion = anoPublicacion;
    }

    @Override
    public String toString() {
        return "Libro{" +
               "id='" + id + '\'' +
               ", titulo='" + titulo + '\'' +
               ", autor='" + autor + '\'' +
               ", anoPublicacion=" + anoPublicacion +
               '}';
    }
}
