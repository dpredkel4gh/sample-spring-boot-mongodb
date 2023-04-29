package com.example.sample.repository;

import com.example.sample.entity.Car;
import com.example.sample.repository.custom.car.CarRepositoryCustom;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CarRepository extends MongoRepository<Car, String>, CarRepositoryCustom {

    Car findByUuid(String uuid);

    long deleteByUuid(String uuid);

    long countByColorId(String id);

    long countByTypeId(String id);
}
