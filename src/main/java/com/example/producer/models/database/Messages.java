package com.example.producer.models.database;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Messages {

    private int id;
    private int author_id;
    private int recipient_id;
    private int content_type;
    private TextContent content;
    private Boolean seen;
    private Boolean received;
    private Calendar created_at;
    private int conversation_id;
}
