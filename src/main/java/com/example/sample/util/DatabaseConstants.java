package com.example.sample.util;

public interface DatabaseConstants {

    interface Car {
        String TABLE = "cars";
        String ID = "id";
        String UUID = "uuid";
        String NAME = "name";
        String TYPE = "type";
        String COLOR = "color";
    }

    interface CarType {
        String TABLE = "car_types";
        String ID = "id";
        String UUID = "uuid";
        String NAME = "name";
    }

    interface Color {
        String TABLE = "colors";
        String ID = "id";
        String UUID = "uuid";
        String NAME = "name";
    }
}
