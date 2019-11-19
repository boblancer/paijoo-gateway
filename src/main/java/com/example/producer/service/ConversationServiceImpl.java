package com.example.producer.service;

import com.example.producer.models.database.Messages;
import com.example.producer.models.database.TextContent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.util.Calendar;

public class ConversationServiceImpl {
    public Messages processMessage(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        Messages m = objectMapper.readValue(json, Messages.class);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Messages> request = new HttpEntity<>(m);
        Messages mes = restTemplate.postForObject("https://paijoo-api.herokuapp.com/messages/post"
                , request, Messages.class);
        return m;
    }

    public static void main(String[] args) {
        Messages m = new Messages(1,1,1,1,
                new TextContent(1009,"hello wr")
                , false, false , Calendar.getInstance(), 1);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Messages> request = new HttpEntity<>(m);
        Messages mes = restTemplate.postForObject("https://paijoo-api.herokuapp.com/messages/post"
                , request, Messages.class);

    }
}
