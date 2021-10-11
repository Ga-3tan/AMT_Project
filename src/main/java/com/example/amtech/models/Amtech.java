package com.example.amtech.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

@Data
@Builder
@AllArgsConstructor
@Document
public class Amtech {

    @Id
    final String id;

    @Field
    String name;

    @Field
    String owner;
}
