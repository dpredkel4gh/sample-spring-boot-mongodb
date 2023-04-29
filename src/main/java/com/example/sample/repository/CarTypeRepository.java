package com.example.sample.repository;

import com.example.sample.entity.CarType;
import com.example.sample.repository.custom.type.CarTypeRepositoryCustom;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CarTypeRepository extends MongoRepository<CarType, String>, CarTypeRepositoryCustom {

    CarType findByUuid(String uuid);

    long deleteByUuid(String uuid);
}
