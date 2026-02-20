package org.example.repository;

import org.example.model.Libro;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends MongoRepository<Libro, String> { // <-- Aquí el cambio
    Libro findByTitulo(String titulo);
}