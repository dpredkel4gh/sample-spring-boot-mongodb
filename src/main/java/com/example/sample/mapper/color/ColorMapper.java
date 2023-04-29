package com.example.sample.mapper.color;

import com.example.sample.entity.Color;
import com.example.sample.mapper.Mapper;
import com.example.sample.model.dto.ColorDTO;
import com.example.sample.model.dto.CreateColorDTO;
import com.example.sample.model.vo.ColorVO;

public interface ColorMapper extends Mapper<Color, CreateColorDTO> {

    Color map(ColorDTO source);

    Color map(ColorVO source);
}
