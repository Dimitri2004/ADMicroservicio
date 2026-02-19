package org.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Libros")
public class Libro {
    @Id
    private Long id; // Corresponds to Saga's ID

    private String titulo; // From Saga
    private int parte; // From Saga
    private int anoinicio; // From Saga
    private String ambientacion; // From Saga

    private List<PersonajeDetalle> personajes; // Embedded Personaxe details

    public Libro() {
    }

    public Libro(Long id, String titulo, int parte, int anoinicio, String ambientacion, List<PersonajeDetalle> personajes) {
        this.id = id;
        this.titulo = titulo;
        this.parte = parte;
        this.anoinicio = anoinicio;
        this.ambientacion = ambientacion;
        this.personajes = personajes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getParte() {
        return parte;
    }

    public void setParte(int parte) {
        this.parte = parte;
    }

    public int getAnoinicio() {
        return anoinicio;
    }

    public void setAnoinicio(int anoinicio) {
        this.anoinicio = anoinicio;
    }

    public String getAmbientacion() {
        return ambientacion;
    }

    public void setAmbientacion(String ambientacion) {
        this.ambientacion = ambientacion;
    }

    public List<PersonajeDetalle> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(List<PersonajeDetalle> personajes) {
        this.personajes = personajes;
    }

    // Inner class for Personaje details
    public static class PersonajeDetalle {
        private String nome; // From Personaxe
        private String stand; // From Personaxe

        public PersonajeDetalle() {
        }

        public PersonajeDetalle(String nome, String stand) {
            this.nome = nome;
            this.stand = stand;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getStand() {
            return stand;
        }

        public void setStand(String stand) {
            this.stand = stand;
        }
    }
}
