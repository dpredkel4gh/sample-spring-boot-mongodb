package com.example.sample.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Version")
@RestController
@RequestMapping("/version")
public class VersionController {

    @Value("${app.version}")
    private String version;

    @GetMapping
    @ApiOperation(value = "Version")
    public ResponseEntity<String> version() {
        return ResponseEntity.ok(version);
    }

}
