package org.example;

import org.example.model.Libro;
import org.example.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Secuencia {

    @Autowired
    private LibroService libroService;

    public void executar() {
        System.out.println("--- Iniciando operaciones con Libros ---");

        // 1. Crear y guardar algunos libros
        Libro libro1 = new Libro("1", "Cien años de soledad", "Gabriel García Márquez", 1967);
        Libro libro2 = new Libro("2", "Don Quijote de la Mancha", "Miguel de Cervantes", 1605);
        Libro libro3 = new Libro("3", "1984", "George Orwell", 1949);

        libroService.save(libro1);
        libroService.save(libro2);
        libroService.save(libro3);
        System.out.println("Libros guardados: " + libro1.getTitulo() + ", " + libro2.getTitulo() + ", " + libro3.getTitulo());

        // 2. Buscar todos los libros
        List<Libro> libros = libroService.findAll();
        System.out.println("\n--- Todos los libros en la base de datos ---");
        libros.forEach(System.out::println);

        // 3. Buscar un libro por ID
        Optional<Libro> foundLibro = libroService.findById("2");
        foundLibro.ifPresent(libro -> System.out.println("\nLibro encontrado por ID '2': " + libro));

        // 4. Actualizar un libro
        if (foundLibro.isPresent()) {
            Libro libroToUpdate = foundLibro.get();
            libroToUpdate.setAnoPublicacion(1615); // Segunda parte del Quijote
            libroService.save(libroToUpdate);
            System.out.println("\nLibro actualizado: " + libroToUpdate);
        }

        // 5. Buscar todos los libros después de la actualización
        libros = libroService.findAll();
        System.out.println("\n--- Todos los libros después de la actualización ---");
        libros.forEach(System.out::println);

        // 6. Eliminar un libro por ID
        libroService.deleteById("1");
        System.out.println("\nLibro con ID '1' eliminado.");

        // 7. Buscar todos los libros después de la eliminación
        libros = libroService.findAll();
        System.out.println("\n--- Todos los libros después de la eliminación ---");
        libros.forEach(System.out::println);

        System.out.println("\n--- Operaciones con Libros finalizadas ---");
    }
}
