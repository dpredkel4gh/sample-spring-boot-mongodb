package com.example.sample.mapper.car;

import com.example.sample.entity.Car;
import com.example.sample.mapper.Mapper;
import com.example.sample.model.dto.CarDTO;
import com.example.sample.model.dto.CreateCarDTO;

public interface CarMapper extends Mapper<Car, CreateCarDTO> {

    Car map(CarDTO source);
}
