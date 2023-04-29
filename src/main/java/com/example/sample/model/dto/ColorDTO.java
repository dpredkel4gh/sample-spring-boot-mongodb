package com.example.sample.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ColorDTO {

    @NotBlank
    @JsonProperty("uuid")
    private String uuid;

    @NotBlank
    @JsonProperty("name")
    private String name;
}
