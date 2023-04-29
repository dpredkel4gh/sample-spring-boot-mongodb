package com.example.sample.mapper.car;

import com.example.sample.entity.Car;
import com.example.sample.model.dto.CarDTO;
import com.example.sample.model.dto.CreateCarDTO;
import org.springframework.stereotype.Service;

@Service
public class CarMapperImpl implements CarMapper {

    @Override
    public Car map(CarDTO source) {
        if (source == null)
            return null;

        return Car.builder()
                .uuid(source.getUuid())
                .name(source.getName())
                .build();
    }

    @Override
    public Car map(CreateCarDTO source) {
        if (source == null)
            return null;

        return Car.builder()
                .name(source.getName())
                .build();
    }
}
