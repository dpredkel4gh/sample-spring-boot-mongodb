package com.example.sample.mapper.type;

import com.example.sample.entity.CarType;
import com.example.sample.model.dto.CarTypeDTO;
import org.springframework.stereotype.Service;

@Service
public class CarTypeMapperDTOImpl implements CarTypeMapperDTO {

    @Override
    public CarTypeDTO map(CarType source) {
        if (source == null)
            return null;

        return CarTypeDTO.builder()
                .uuid(source.getUuid())
                .name(source.getName())
                .build();
    }
}
