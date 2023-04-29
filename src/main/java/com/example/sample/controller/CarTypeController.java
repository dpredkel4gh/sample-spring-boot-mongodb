package com.example.sample.controller;

import com.example.sample.model.dto.CarTypeDTO;
import com.example.sample.model.dto.CreateCarTypeDTO;
import com.example.sample.service.type.CarTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Api(value = "Car type")
@RestController
@RequestMapping("/v1/car/type")
public class CarTypeController {

    @Autowired
    private CarTypeService carTypeService;

    @GetMapping
    @ApiOperation(value = "Get list of all available car types")
    public ResponseEntity<Collection<CarTypeDTO>> findAll() {
        return ResponseEntity.ok(carTypeService.findAll());
    }

    @GetMapping("/{uuid}")
    @ApiOperation(value = "Get car type by uuid")
    public ResponseEntity<CarTypeDTO> findByUuid(@PathVariable("uuid") String uuid) {
        CarTypeDTO type = carTypeService.findByUuid(uuid);
        if (type == null)
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();

        return ResponseEntity.ok(type);
    }

    @PostMapping
    @ApiOperation(value = "Save new car type")
    public ResponseEntity<CarTypeDTO> save(@Valid @RequestBody CreateCarTypeDTO dto) {
        CarTypeDTO type = carTypeService.save(dto);
        return ResponseEntity.ok(type);
    }

    @PutMapping
    @ApiOperation(value = "Update car type")
    public ResponseEntity<CarTypeDTO> update(@Valid @RequestBody CarTypeDTO dto) {
        carTypeService.update(dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{uuid}")
    @ApiOperation(value = "Delete car type by uuid")
    public ResponseEntity<HttpStatus> delete(@PathVariable("uuid") String uuid) {
        carTypeService.deleteByUuid(uuid);
        return ResponseEntity.ok().build();
    }

}
