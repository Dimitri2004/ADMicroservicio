package org.example.service;

import org.example.model.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ConexionPostgresService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String POSTGRES_BASE_URL_LIBRO = "http://localhost:8081/postgres/libros";

    public List<Libro> buscarLibros() {
        try {
            String url = POSTGRES_BASE_URL_LIBRO;
            ResponseEntity<List<Libro>> response = restTemplate.exchange(
                    url, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Libro>>() {}
            );
            return response.getBody() != null ? response.getBody() : Collections.emptyList();
        } catch (HttpClientErrorException e) {
            System.out.println("Erro al buscar libros en Postgres: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public boolean borrarLibro(String id) { // Changed ID type to String
        try {
            String url = POSTGRES_BASE_URL_LIBRO + "/" + id;
            ResponseEntity<Void> response = restTemplate.exchange(
                    url, HttpMethod.DELETE, null, Void.class
            );
            return true;
        } catch (HttpClientErrorException e) {
            System.out.println("Error al borrar libro en Postgres: " + e.getMessage());
            return false;
        }
    }

    public Libro crearLibro(Libro libro) {
        try {
            String url = POSTGRES_BASE_URL_LIBRO;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Libro> request = new HttpEntity<>(libro, headers);

            ResponseEntity<Libro> response = restTemplate.exchange(
                    url, HttpMethod.POST, request, Libro.class
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            System.out.println("Erro al crear libro en Postgres: " + e.getMessage());
            return null;
        }
    }

    public Optional<Libro> libroPorID(String id) { // Changed ID type to String
        try {
            String url = POSTGRES_BASE_URL_LIBRO + "/" + id;
            ResponseEntity<Libro> response = restTemplate.exchange(url, HttpMethod.GET, null, Libro.class);
            return Optional.ofNullable(response.getBody());
        } catch (HttpClientErrorException e) {
            System.out.println("Error al buscar libro por ID en Postgres: " + e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<Libro> libroPorTitulo(String titulo) {
        try {
            String url = POSTGRES_BASE_URL_LIBRO + "/titulo/" + titulo;
            ResponseEntity<List<Libro>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Libro>>() {});
            List<Libro> libros = response.getBody();
            return libros != null && !libros.isEmpty() ? Optional.of(libros.get(0)) : Optional.empty();
        } catch (HttpClientErrorException e) {
            System.out.println("Error al buscar libro por título en Postgres: " + e.getMessage());
            return Optional.empty();
        }
    }
}
