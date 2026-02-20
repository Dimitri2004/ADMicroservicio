package org.example;

import org.example.model.Libro;
import org.example.service.ConexionMongoService;
import org.example.service.ConexionPostgresService;
import org.example.service.JSONService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID; // For generating unique String IDs

@Service
public class Secuencia {

    @Autowired
    private ConexionPostgresService conexionPostgresService;
    @Autowired
    private ConexionMongoService conexionMongoService;
    @Autowired
    private JSONService jsonService;


    public void executar() {

        Libro libro1 = new Libro();
        libro1.setId("123456");
        libro1.setTitulo("El Quijote");
        libro1.setAutor("Miguel de Cervantes");
        libro1.setAnoPublicacion(1605);

        // Intentar crear en Postgres
        Libro guardado = conexionPostgresService.crearLibro(libro1);

        if (guardado == null) {
            System.err.println("CRÍTICO: Postgres (8081) rechazó el libro. ¿Borraste la tabla y quitaste el @GeneratedValue?");
            // Opcional: return; para no seguir si Postgres falla
        } else {
            libro1 = guardado;
            System.out.println("Libro guardado correctamente: " + libro1);
        }

        // Example: Find a book by title from Postgres and save it to Mongo
        Optional<Libro> libro3Optional = conexionPostgresService.libroPorTitulo("Cien años de soledad");
        libro3Optional.ifPresent(libro -> {
            conexionMongoService.crearLibro(libro);
            System.out.println("Libro '" + libro.getTitulo() + "' de Postgres guardado en Mongo.");
        });

        // Get all books from Postgres and save them to Mongo
        List<Libro> allLibrosPostgres = conexionPostgresService.buscarLibros();
        System.out.println("\nGuardando todos los libros de Postgres en Mongo:");
        for (Libro libro : allLibrosPostgres) {
            conexionMongoService.crearLibro(libro);
            System.out.println("  - Libro: " + libro.getTitulo() + " (ID: " + libro.getId() + ")");
        }

        // Export all books from Mongo to JSON
        List<Libro> allLibrosMongo = conexionMongoService.buscarLibros();
        jsonService.exportarJSONLibros(allLibrosMongo);
        System.out.println("\nLibros exportados a JSON.");

//        // Delete the created book from Postgres
//        if (libro1.getId() != null) {
//            boolean deleted = conexionPostgresService.borrarLibro(libro1.getId());
//            System.out.println("Libro con ID " + libro1.getId() + " borrado de Postgres: " + deleted);
//        }
    }
}
