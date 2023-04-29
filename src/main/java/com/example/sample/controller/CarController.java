package com.example.sample.controller;

import com.example.sample.model.dto.CarDTO;
import com.example.sample.model.dto.CreateCarDTO;
import com.example.sample.service.car.CarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Api(value = "Car")
@RestController
@RequestMapping("/v1/car")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    @ApiOperation(value = "Get list of all available cars")
    public ResponseEntity<Collection<CarDTO>> findAll() {
        return ResponseEntity.ok(carService.findAll());
    }

    @GetMapping("/{uuid}")
    @ApiOperation(value = "Get car by uuid")
    public ResponseEntity<CarDTO> findByUuid(@PathVariable("uuid") String uuid) {
        CarDTO car = carService.findByUuid(uuid);
        if (car == null)
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();

        return ResponseEntity.ok(car);
    }

    @PostMapping
    @ApiOperation(value = "Save new car")
    public ResponseEntity<CarDTO> save(@Valid @RequestBody CreateCarDTO dto) {
        CarDTO car = carService.save(dto);
        return ResponseEntity.ok(car);
    }

    @PutMapping
    @ApiOperation(value = "Update car")
    public ResponseEntity<CarDTO> update(@Valid @RequestBody CarDTO dto) {
        carService.update(dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{uuid}")
    @ApiOperation(value = "Delete car by uuid")
    public ResponseEntity<CarDTO> delete(@PathVariable("uuid") String uuid) {
        carService.deleteByUuid(uuid);
        return ResponseEntity.ok().build();
    }
}
