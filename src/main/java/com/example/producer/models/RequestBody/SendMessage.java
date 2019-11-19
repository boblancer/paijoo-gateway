package com.example.producer.models.RequestBody;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;

@Data
@NoArgsConstructor
public class SendMessage<C extends ContentRequestBody> {

    private int author_id;
    private int recipient_id;
    private int content_type;
    private Calendar created_at;
    private C content;
    private int conversation_id;
}
