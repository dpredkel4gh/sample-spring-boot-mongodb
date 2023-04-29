package com.example.sample.util;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

@Component
public class ExecutorHolder {

    @Getter
    @Autowired
    private Executor executor;

}
