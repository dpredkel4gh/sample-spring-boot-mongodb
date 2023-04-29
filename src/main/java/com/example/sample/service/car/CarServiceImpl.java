package com.example.sample.service.car;

import com.example.sample.entity.Car;
import com.example.sample.exception.ErrorCode;
import com.example.sample.exception.ServiceRuntimeException;
import com.example.sample.mapper.car.CarMapper;
import com.example.sample.mapper.car.CarMapperDTO;
import com.example.sample.mapper.color.ColorMapper;
import com.example.sample.mapper.type.CarTypeMapper;
import com.example.sample.model.dto.CarDTO;
import com.example.sample.model.dto.CreateCarDTO;
import com.example.sample.model.vo.CarTypeVO;
import com.example.sample.model.vo.ColorVO;
import com.example.sample.repository.CarRepository;
import com.example.sample.service.color.ColorService;
import com.example.sample.service.msg.MsgService;
import com.example.sample.service.type.CarTypeService;
import com.example.sample.util.ExecutorHolder;
import com.example.sample.util.Generator;
import com.example.sample.util.MsgCode;
import com.example.sample.util.NumberConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarMapperDTO carMapperDto;

    @Autowired
    private CarMapper carMapper;

    @Autowired
    private CarTypeService carTypeService;

    @Autowired
    private ColorService colorService;

    @Autowired
    private CarTypeMapper carTypeMapper;

    @Autowired
    private ColorMapper colorMapper;

    @Autowired
    private MsgService msgService;

    @Autowired
    private ExecutorHolder executorHolder;

    @Override
    public Collection<CarDTO> findAll() {
        return carMapperDto.map(carRepository.findAll());
    }

    @Override
    public CarDTO findByUuid(String uuid) {
        Car car = carRepository.findByUuid(uuid);
        if (car == null)
            throw new ServiceRuntimeException(msgService.msg(MsgCode.C_0003, uuid), ErrorCode.VALIDATION_ERROR.getValue());
        return carMapperDto.map(car);
    }

    @Override
    public CarDTO save(CreateCarDTO dto) {
        Car entity = carMapper.map(dto);
        entity.setId(Generator.uuid());
        entity.setUuid(Generator.uuid());
        setTypeAndColor(entity, dto.getTypeUuid(), dto.getColorUuid());
        Car saved = carRepository.insert(entity);
        return carMapperDto.map(saved);
    }

    @Override
    public void deleteByUuid(String uuid) {
        long deleted = carRepository.deleteByUuid(uuid);
        if (deleted == NumberConstants.ZERO)
            throw new ServiceRuntimeException(msgService.msg(MsgCode.C_0003, uuid), ErrorCode.VALIDATION_ERROR.getValue());
    }

    @Override
    public void update(CarDTO dto) {
        Car entity = carMapper.map(dto);
        setTypeAndColor(entity, dto.getTypeUuid(), dto.getColorUuid());
        long updated = carRepository.update(entity);
        if (updated == NumberConstants.ZERO)
            throw new ServiceRuntimeException(msgService.msg(MsgCode.C_0007, dto.getUuid()), ErrorCode.VALIDATION_ERROR.getValue());
    }

    private void setTypeAndColor(Car entity, String typeUuid, String colorUuid) {
        CompletableFuture<CarTypeVO> typeFuture = CompletableFuture
                .supplyAsync(() -> carTypeService.findByUuidVo(typeUuid), executorHolder.getExecutor())
                .whenComplete((future, throwable) -> {
                    if (throwable == null)
                        entity.setType(carTypeMapper.map(future));
                });

        CompletableFuture<ColorVO> colorFuture = CompletableFuture
                .supplyAsync(() -> colorService.findByUuidVo(colorUuid), executorHolder.getExecutor())
                .whenComplete((future, throwable) -> {
                    if (throwable == null)
                        entity.setColor(colorMapper.map(future));
                });

        try {
//          We have to wait,
//          because after this we will persist entity to DB and fields must be set
            CompletableFuture.allOf(typeFuture, colorFuture).join();
        } catch (Exception e) {
            if (e.getCause().getClass().equals(ServiceRuntimeException.class))
                throw (ServiceRuntimeException) e.getCause();
            else
                throw new ServiceRuntimeException(msgService.msg(MsgCode.C_0004), e);
        }
    }

    @Override
    public long countByColorId(String id) {
        return carRepository.countByColorId(id);
    }

    @Override
    public long countByTypeId(String id) {
        return carRepository.countByTypeId(id);
    }
}
