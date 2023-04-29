package com.example.sample.mapper.type;

import com.example.sample.entity.CarType;
import com.example.sample.model.dto.CarTypeDTO;
import com.example.sample.model.dto.CreateCarTypeDTO;
import com.example.sample.model.vo.CarTypeVO;
import org.springframework.stereotype.Service;

@Service
public class CarTypeMapperImpl implements CarTypeMapper {

    @Override
    public CarType map(CarTypeDTO source) {
        if (source == null)
            return null;

        return CarType.builder()
                .uuid(source.getUuid())
                .name(source.getName())
                .build();
    }

    @Override
    public CarType map(CarTypeVO source) {
        if (source == null)
            return null;

        return CarType.builder()
                .id(source.getId())
                .uuid(source.getUuid())
                .name(source.getName())
                .build();
    }

    @Override
    public CarType map(CreateCarTypeDTO source) {
        if (source == null)
            return null;

        return CarType.builder()
                .name(source.getName())
                .build();
    }
}
