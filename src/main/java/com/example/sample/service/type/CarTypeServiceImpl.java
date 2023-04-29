package com.example.sample.service.type;

import com.example.sample.entity.CarType;
import com.example.sample.exception.ErrorCode;
import com.example.sample.exception.ServiceRuntimeException;
import com.example.sample.mapper.type.CarTypeMapper;
import com.example.sample.mapper.type.CarTypeMapperDTO;
import com.example.sample.mapper.type.CarTypeMapperVO;
import com.example.sample.model.dto.CarTypeDTO;
import com.example.sample.model.dto.CreateCarTypeDTO;
import com.example.sample.model.vo.CarTypeVO;
import com.example.sample.repository.CarTypeRepository;
import com.example.sample.service.car.CarService;
import com.example.sample.service.msg.MsgService;
import com.example.sample.util.Generator;
import com.example.sample.util.MsgCode;
import com.example.sample.util.NumberConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
public class CarTypeServiceImpl implements CarTypeService {

    @Autowired
    private CarTypeRepository carTypeRepository;

    @Autowired
    private CarTypeMapperDTO carTypeMapperDto;

    @Autowired
    private CarTypeMapperVO carTypeMapperVo;

    @Autowired
    private CarTypeMapper carTypeMapper;

    @Autowired
    private CarService carService;

    @Autowired
    private MsgService msgService;

    @Override
    public Collection<CarTypeDTO> findAll() {
        return carTypeMapperDto.map(carTypeRepository.findAll());
    }

    @Override
    public CarTypeDTO findByUuid(String uuid) {
        CarType type = carTypeRepository.findByUuid(uuid);
        if (type == null)
            throw new ServiceRuntimeException(msgService.msg(MsgCode.C_0001, uuid), ErrorCode.VALIDATION_ERROR.getValue());
        return carTypeMapperDto.map(type);
    }

    @Override
    public CarTypeVO findByUuidVo(String uuid) {
        CarType type = carTypeRepository.findByUuid(uuid);
        if (type == null)
            throw new ServiceRuntimeException(msgService.msg(MsgCode.C_0001, uuid), ErrorCode.VALIDATION_ERROR.getValue());
        return carTypeMapperVo.map(type);
    }

    @Override
    public CarTypeDTO save(CreateCarTypeDTO dto) {
        CarType entity = carTypeMapper.map(dto);
        entity.setId(Generator.uuid());
        entity.setUuid(Generator.uuid());
        CarType saved = carTypeRepository.insert(entity);
        return carTypeMapperDto.map(saved);
    }

    @Override
    public void deleteByUuid(String uuid) {
        CarType type = carTypeRepository.findByUuid(uuid);
        if (type == null)
            throw new ServiceRuntimeException(msgService.msg(MsgCode.C_0001, uuid), ErrorCode.VALIDATION_ERROR.getValue());

        long cars = carService.countByTypeId(type.getId());
        if (cars > NumberConstants.ZERO)
            throw new ServiceRuntimeException(msgService.msg(MsgCode.C_0006, uuid), ErrorCode.VALIDATION_ERROR.getValue());

        long deleted = carTypeRepository.deleteByUuid(uuid);
        if (deleted == NumberConstants.ZERO)
            throw new ServiceRuntimeException(msgService.msg(MsgCode.C_0001, uuid), ErrorCode.VALIDATION_ERROR.getValue());
    }

    @Override
    public void update(CarTypeDTO dto) {
        CarType entity = carTypeMapper.map(dto);
        long updated = carTypeRepository.update(entity);
        if (updated == NumberConstants.ZERO)
            throw new ServiceRuntimeException(msgService.msg(MsgCode.C_0008, dto.getUuid()), ErrorCode.VALIDATION_ERROR.getValue());
    }

}
