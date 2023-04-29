package com.example.sample.entity;

import com.example.sample.util.DatabaseConstants;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = DatabaseConstants.CarType.TABLE)
public class CarType {

    @Id
    @Field(DatabaseConstants.CarType.ID)
    private String id;

    @Indexed(unique = true)
    @Field(DatabaseConstants.CarType.UUID)
    private String uuid;

    @Indexed(unique = true)
    @Field(DatabaseConstants.CarType.NAME)
    private String name;
}
