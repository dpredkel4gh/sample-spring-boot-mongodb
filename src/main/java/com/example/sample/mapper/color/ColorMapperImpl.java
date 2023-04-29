package com.example.sample.mapper.color;

import com.example.sample.entity.Color;
import com.example.sample.model.dto.ColorDTO;
import com.example.sample.model.dto.CreateColorDTO;
import com.example.sample.model.vo.ColorVO;
import org.springframework.stereotype.Service;

@Service
public class ColorMapperImpl implements ColorMapper {

    @Override
    public Color map(CreateColorDTO source) {
        if (source == null)
            return null;

        return Color.builder()
                .name(source.getName())
                .build();
    }

    @Override
    public Color map(ColorDTO source) {
        if (source == null)
            return null;

        return Color.builder()
                .uuid(source.getUuid())
                .name(source.getName())
                .build();
    }

    @Override
    public Color map(ColorVO source) {
        if (source == null)
            return null;

        return Color.builder()
                .id(source.getId())
                .uuid(source.getUuid())
                .name(source.getName())
                .build();
    }
}
