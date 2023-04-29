package com.example.sample.entity;

import com.example.sample.util.DatabaseConstants;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = DatabaseConstants.Color.TABLE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Color {

    @Id
    @Field(DatabaseConstants.Color.ID)
    private String id;

    @Indexed(unique = true)
    @Field(DatabaseConstants.Color.UUID)
    private String uuid;

    @Indexed(unique = true)
    @Field(DatabaseConstants.Color.NAME)
    private String name;
}
