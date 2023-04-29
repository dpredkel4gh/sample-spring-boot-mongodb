package com.example.sample.mapper.color;

import com.example.sample.entity.Color;
import com.example.sample.model.vo.ColorVO;
import org.springframework.stereotype.Service;

@Service
public class ColorMapperVOImpl implements ColorMapperVO {

    @Override
    public ColorVO map(Color source) {
        if (source == null)
            return null;

        return ColorVO.builder()
                .id(source.getId())
                .uuid(source.getUuid())
                .name(source.getName())
                .build();
    }
}
