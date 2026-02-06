package org.example.service;

import com.google.gson.Gson;
import org.example.model.Losjojos;
import org.example.model.Saga;
import org.example.repository.LosJojosRepository;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.util.List;

@Service
public class LosJoJosService {
    private LosJojosRepository losJojosRepository;

    public LosJoJosService(LosJojosRepository losJojosRepository) {
        this.losJojosRepository = losJojosRepository;
    }

    public Losjojos save(Losjojos losJojos) {
        return losJojosRepository.save(losJojos);
    }

    public void exportarJson(){
        Gson gson = new Gson();
        List<Losjojos> losjojos = losJojosRepository.findAll();
        try (FileWriter escritor = new FileWriter("src/main/java/org/example/Json/LosJojos.json")){
            String json = gson.toJson(losjojos);
            escritor.write(json);
        } catch (Exception e) {
            System.out.println("Error al exportar. "+e.getMessage());
        }
    }
    public List<Losjojos> findAll() {
        return losJojosRepository.findAll();
    }
    
}
