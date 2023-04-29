package com.example.sample.service.car;

import com.example.sample.model.dto.CarDTO;
import com.example.sample.model.dto.CreateCarDTO;
import com.example.sample.service.Service;

public interface CarService extends Service<CarDTO, CreateCarDTO> {

    long countByColorId(String id);

    long countByTypeId(String id);
}
