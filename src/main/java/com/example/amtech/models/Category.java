package com.example.amtech.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Document(collection = "categories")
public class Category {
    @Id
    private String id;

    @NotBlank(message = "This field must not be blank")
    private String name;
}

