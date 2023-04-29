package com.example.sample.service.color;

import com.example.sample.entity.Color;
import com.example.sample.exception.ErrorCode;
import com.example.sample.exception.ServiceRuntimeException;
import com.example.sample.mapper.color.ColorMapper;
import com.example.sample.mapper.color.ColorMapperDTO;
import com.example.sample.mapper.color.ColorMapperVO;
import com.example.sample.model.dto.ColorDTO;
import com.example.sample.model.dto.CreateColorDTO;
import com.example.sample.model.vo.ColorVO;
import com.example.sample.repository.ColorRepository;
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
public class ColorServiceImpl implements ColorService {

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private ColorMapperDTO colorMapperDto;

    @Autowired
    private ColorMapperVO colorMapperVo;

    @Autowired
    private ColorMapper colorMapper;

    @Autowired
    private CarService carService;

    @Autowired
    private MsgService msgService;

    @Override
    public Collection<ColorDTO> findAll() {
        return colorMapperDto.map(colorRepository.findAll());
    }

    @Override
    public ColorDTO findByUuid(String uuid) {
        Color color = colorRepository.findByUuid(uuid);
        if (color == null)
            throw new ServiceRuntimeException(msgService.msg(MsgCode.C_0002, uuid), ErrorCode.VALIDATION_ERROR.getValue());
        return colorMapperDto.map(color);
    }

    @Override
    public ColorVO findByUuidVo(String uuid) {
        Color color = colorRepository.findByUuid(uuid);
        if (color == null)
            throw new ServiceRuntimeException(msgService.msg(MsgCode.C_0002, uuid), ErrorCode.VALIDATION_ERROR.getValue());
        return colorMapperVo.map(color);
    }

    @Override
    public ColorDTO save(CreateColorDTO dto) {
        Color entity = colorMapper.map(dto);
        entity.setId(Generator.uuid());
        entity.setUuid(Generator.uuid());
        Color saved = colorRepository.insert(entity);
        return colorMapperDto.map(saved);
    }

    @Override
    public void deleteByUuid(String uuid) {
        Color color = colorRepository.findByUuid(uuid);
        if (color == null)
            throw new ServiceRuntimeException(msgService.msg(MsgCode.C_0002, uuid), ErrorCode.VALIDATION_ERROR.getValue());

        long cars = carService.countByColorId(color.getId());
        if (cars > NumberConstants.ZERO)
            throw new ServiceRuntimeException(msgService.msg(MsgCode.C_0005, uuid), ErrorCode.VALIDATION_ERROR.getValue());

        long deleted = colorRepository.deleteByUuid(uuid);
        if (deleted == NumberConstants.ZERO)
            throw new ServiceRuntimeException(msgService.msg(MsgCode.C_0002, uuid), ErrorCode.VALIDATION_ERROR.getValue());
    }

    @Override
    public void update(ColorDTO dto) {
        Color entity = colorMapper.map(dto);
        long updated = colorRepository.update(entity);
        if (updated == NumberConstants.ZERO)
            throw new ServiceRuntimeException(msgService.msg(MsgCode.C_0009, dto.getUuid()), ErrorCode.VALIDATION_ERROR.getValue());
    }

}
