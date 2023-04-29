package com.example.sample.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommonErrorDetail {

    @JsonProperty("title")
    private String title;

    @JsonProperty("status")
    private int status;

    @JsonProperty("detail")
    private String detail;

    @JsonProperty("timestamp")
    private long timestamp;

    @JsonProperty("exception")
    private String exception;

    @JsonProperty("method")
    private String method;

    @JsonProperty("requestedPath")
    private String requestedPath;

}
