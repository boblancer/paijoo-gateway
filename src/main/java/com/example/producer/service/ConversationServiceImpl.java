package com.example.producer.service;

import com.example.producer.models.database.Messages;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.IOException;

public class ConversationServiceImpl {
    public void processMessage(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        Messages m = objectMapper.readValue(json, Messages.class);
        System.out.println(m);
    }
}
