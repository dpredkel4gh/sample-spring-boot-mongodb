package com.example.sample.util;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.NoArgGenerator;

public abstract class Generator {

    private static final NoArgGenerator UUID_GENERATOR = Generators.timeBasedGenerator();

    public static String uuid() {
        return UUID_GENERATOR.generate().toString();
    }
}
