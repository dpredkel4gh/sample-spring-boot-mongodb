package com.example.sample.service.type;

import com.example.sample.model.dto.CarTypeDTO;
import com.example.sample.model.dto.CreateCarTypeDTO;
import com.example.sample.model.vo.CarTypeVO;
import com.example.sample.service.Service;

public interface CarTypeService extends Service<CarTypeDTO, CreateCarTypeDTO> {

    CarTypeVO findByUuidVo(String uuid);

}
