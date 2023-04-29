package com.example.sample.entity;

import com.example.sample.util.DatabaseConstants;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = DatabaseConstants.Car.TABLE)
public class Car {

    @Id
    @Field(DatabaseConstants.Car.ID)
    private String id;

    @Indexed(unique = true)
    @Field(DatabaseConstants.Car.UUID)
    private String uuid;

    @Field(DatabaseConstants.Car.NAME)
    private String name;

    @DBRef
    @Field(DatabaseConstants.Car.TYPE)
    private CarType type;

    @DBRef
    @Field(DatabaseConstants.Car.COLOR)
    private Color color;
}
