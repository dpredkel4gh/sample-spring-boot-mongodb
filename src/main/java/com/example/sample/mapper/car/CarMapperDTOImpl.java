package com.example.sample.mapper.car;

import com.example.sample.entity.Car;
import com.example.sample.model.dto.CarDTO;
import org.springframework.stereotype.Service;

@Service
public class CarMapperDTOImpl implements CarMapperDTO {

    @Override
    public CarDTO map(Car source) {
        if (source == null)
            return null;

        return CarDTO.builder()
                .uuid(source.getUuid())
                .name(source.getName())
                .typeUuid(source.getType().getUuid())
                .colorUuid(source.getColor().getUuid())
                .build();
    }
}
