package org.example.service;

import com.google.gson.Gson;
import org.example.model.Libro;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.util.List;

@Service
public class JSONService {
    public void exportarJSONLibros(List<Libro> libros){
        Gson gson = new Gson();
        try (FileWriter escritor = new FileWriter("src/main/java/org/example/Json/libros.json")){
            String json = gson.toJson(libros);
            escritor.write(json);
        } catch (Exception e) {
            System.out.println("Error al exportar. "+e.getMessage());
        }
    }
}
