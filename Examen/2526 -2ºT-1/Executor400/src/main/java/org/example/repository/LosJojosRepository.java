package org.example.repository;

import org.example.model.Losjojos;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LosJojosRepository extends MongoRepository<Losjojos,String> {
}
