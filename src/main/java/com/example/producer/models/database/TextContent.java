package com.example.producer.models.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TextContent {
    private int id;
    private String content;
}
