package com.example.producer.models.RequestBody;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TextContentRequestBody implements ContentRequestBody {
    private String text;
}
