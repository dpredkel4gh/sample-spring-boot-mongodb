package com.example.sample.mapper.type;

import com.example.sample.entity.CarType;
import com.example.sample.model.vo.CarTypeVO;
import org.springframework.stereotype.Service;

@Service
public class CarTypeMapperVOImpl implements CarTypeMapperVO {

    @Override
    public CarTypeVO map(CarType source) {
        if (source == null)
            return null;

        return CarTypeVO.builder()
                .id(source.getId())
                .uuid(source.getUuid())
                .name(source.getName())
                .build();
    }
}
