package com.example.producer.handlers;

import com.example.producer.service.ConversationServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TextHandler extends TextWebSocketHandler {
    private Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
    private Map<String, String> idToSession = new ConcurrentHashMap<>();
    private ConversationServiceImpl conversationService;

    public TextHandler(){
         conversationService = new ConversationServiceImpl();}


    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        System.out.println("payload" + message.getPayload());
        conversationService.processMessage(message.getPayload());

    }


    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session.getId());
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println(session + "connected");
        sessions.put(session.getId(), session);
    }
}
