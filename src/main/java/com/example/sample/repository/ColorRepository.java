package com.example.sample.repository;

import com.example.sample.entity.Color;
import com.example.sample.repository.custom.color.ColorRepositoryCustom;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ColorRepository extends MongoRepository<Color, String>, ColorRepositoryCustom {

    Color findByUuid(String uuid);

    long deleteByUuid(String uuid);
}
