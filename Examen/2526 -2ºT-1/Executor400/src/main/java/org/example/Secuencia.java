package org.example;

import org.example.model.Libro;
import org.example.service.ConexionMongoService;
import org.example.service.ConexionPostgresService;
import org.example.service.JSONService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Secuencia {

    @Autowired
    private ConexionPostgresService conexionPostgresService;
    @Autowired
    private ConexionMongoService conexionMongoService;
    @Autowired
    private JSONService jsonService;


    public void executar() {

        ArrayList<Libro.PersonajeDetalle> personajes = new ArrayList<>();
        Libro.PersonajeDetalle p1 = new Libro.PersonajeDetalle("Giorno Giovanna", "Gold Experience");
        personajes.add(p1);

        Libro.PersonajeDetalle p2 = new Libro.PersonajeDetalle("Bruno Bucciarati", "Sticky Fingers");
        personajes.add(p2);

        Libro.PersonajeDetalle p3 = new Libro.PersonajeDetalle("Guido Mista", "Sex Pistols");
        personajes.add(p3);

        Libro libro1 = new Libro();
        libro1.setTitulo("Vento Aureo");
        libro1.setParte(5);
        libro1.setAmbientacion("Italia");
        libro1.setAnoinicio(2001);
        libro1.setPersonajes(personajes);

        // Create a book in Postgres
        libro1 = conexionPostgresService.crearLibro(libro1);
        System.out.println("Libro creado en Postgres: " + libro1.getTitulo());

        // Find a book by ID from Postgres and save it to Mongo
        Optional<Libro> libro2Optional = conexionPostgresService.libroPorID(1L); // Assuming ID 1 exists
        libro2Optional.ifPresent(libro -> {
            conexionMongoService.crearLibro(libro);
            System.out.println("Libro con ID 1 de Postgres guardado en Mongo: " + libro.getTitulo());
        });

        // Find a book by title from Postgres and save it to Mongo
        Optional<Libro> libro3Optional = conexionPostgresService.libroPorTitulo("Stardust Crusaders");
        libro3Optional.ifPresent(libro -> {
            conexionMongoService.crearLibro(libro);
            System.out.println("Libro 'Stardust Crusaders' de Postgres guardado en Mongo: " + libro.getTitulo());
        });

        // Get all books from Postgres and save them to Mongo
        List<Libro> allLibrosPostgres = conexionPostgresService.buscarLibros();
        for (Libro libro : allLibrosPostgres) {
            conexionMongoService.crearLibro(libro);
            System.out.println("Libro de Postgres guardado en Mongo: " + libro.getTitulo());
        }

        // Export all books from Mongo to JSON
        List<Libro> allLibrosMongo = conexionMongoService.buscarLibros();
        jsonService.exportarJSONLibros(allLibrosMongo);
        System.out.println("Libros exportados a JSON.");

        // Delete the created book from Postgres
        if (libro1.getId() != null) {
            boolean deleted = conexionPostgresService.borrarLibro(libro1.getId());
            System.out.println("Libro con ID " + libro1.getId() + " borrado de Postgres: " + deleted);
        }
    }
}
