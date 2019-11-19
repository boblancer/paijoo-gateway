package com.example.producer.models;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;

public class WebSocketTextMessage implements WebSocketMessage<TextMessage> {

    @Override
    public TextMessage getPayload() {
        return null;
    }

    @Override
    public int getPayloadLength() {
        return 0;
    }

    @Override
    public boolean isLast() {
        return false;
    }
}
