package com.example.sample.mapper.type;

import com.example.sample.entity.CarType;
import com.example.sample.mapper.Mapper;
import com.example.sample.model.dto.CarTypeDTO;
import com.example.sample.model.dto.CreateCarTypeDTO;
import com.example.sample.model.vo.CarTypeVO;

public interface CarTypeMapper extends Mapper<CarType, CreateCarTypeDTO> {

    CarType map(CarTypeDTO source);

    CarType map(CarTypeVO source);
}
