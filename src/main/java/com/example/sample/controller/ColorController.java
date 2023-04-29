package com.example.sample.controller;

import com.example.sample.model.dto.CarDTO;
import com.example.sample.model.dto.ColorDTO;
import com.example.sample.model.dto.CreateColorDTO;
import com.example.sample.service.color.ColorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Api(value = "Color")
@RestController
@RequestMapping("/v1/color")
public class ColorController {

    @Autowired
    private ColorService colorService;

    @GetMapping
    @ApiOperation(value = "Get list of all available colors")
    public ResponseEntity<Collection<ColorDTO>> findAll() {
        return ResponseEntity.ok(colorService.findAll());
    }

    @GetMapping("/{uuid}")
    @ApiOperation(value = "Get color by uuid")
    public ResponseEntity<ColorDTO> findByUuid(@PathVariable("uuid") String uuid) {
        ColorDTO color = colorService.findByUuid(uuid);
        if (color == null)
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();

        return ResponseEntity.ok(color);
    }

    @PostMapping
    @ApiOperation(value = "Save new color")
    public ResponseEntity<ColorDTO> save(@Valid @RequestBody CreateColorDTO dto) {
        ColorDTO color = colorService.save(dto);
        return ResponseEntity.ok(color);
    }

    @PutMapping
    @ApiOperation(value = "Update color")
    public ResponseEntity<ColorDTO> update(@Valid @RequestBody ColorDTO dto) {
        colorService.update(dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{uuid}")
    @ApiOperation(value = "Delete color by uuid")
    public ResponseEntity<CarDTO> delete(@PathVariable("uuid") String uuid) {
        colorService.deleteByUuid(uuid);
        return ResponseEntity.ok().build();
    }
}
