package org.example.service;

import com.google.gson.Gson;
import org.example.model.Losjojos;
import org.example.model.Saga;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.util.List;

@Service
public class JSONService {
    public void exportarJSONLosJojos(List<Losjojos> losjojos){
        Gson gson = new Gson();
        try (FileWriter escritor = new FileWriter("src/main/java/org/example/Json/losjojos.json")){
            String json = gson.toJson(losjojos);
            escritor.write(json);
        } catch (Exception e) {
            System.out.println("Error al exportar. "+e.getMessage());
        }
    }
    public void exportarJSONSagass(List<Saga> sagas){
        Gson gson = new Gson();
        try (FileWriter escritor = new FileWriter("src/main/java/org/example/Json/sagas.json")){
            String json = gson.toJson(sagas);
            escritor.write(json);
        } catch (Exception e) {
            System.out.println("Error al exportar. "+e.getMessage());
        }
    }
}
