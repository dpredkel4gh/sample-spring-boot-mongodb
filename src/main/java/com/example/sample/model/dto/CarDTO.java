package com.example.sample.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO {

    @NotBlank
    @JsonProperty("uuid")
    private String uuid;

    @NotBlank
    @JsonProperty("name")
    private String name;

    @NotBlank
    @JsonProperty("typeUuid")
    private String typeUuid;

    @NotBlank
    @JsonProperty("colorUuid")
    private String colorUuid;

}
