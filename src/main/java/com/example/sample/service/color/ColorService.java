package com.example.sample.service.color;

import com.example.sample.model.dto.ColorDTO;
import com.example.sample.model.dto.CreateColorDTO;
import com.example.sample.model.vo.ColorVO;
import com.example.sample.service.Service;

public interface ColorService extends Service<ColorDTO, CreateColorDTO> {

    ColorVO findByUuidVo(String uuid);

}
