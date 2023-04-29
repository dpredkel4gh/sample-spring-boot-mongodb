package com.example.sample.mapper.color;

import com.example.sample.entity.Color;
import com.example.sample.model.dto.ColorDTO;
import org.springframework.stereotype.Service;

@Service
public class ColorMapperDTOImpl implements ColorMapperDTO {

    @Override
    public ColorDTO map(Color source) {
        if (source == null)
            return null;

        return ColorDTO.builder()
                .uuid(source.getUuid())
                .name(source.getName())
                .build();
    }
}
