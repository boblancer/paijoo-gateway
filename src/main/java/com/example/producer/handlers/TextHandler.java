package com.example.producer.handlers;

import com.example.producer.models.database.Messages;
import com.example.producer.service.ConversationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
    private Map<Integer, String> userId_to_sessionId = new ConcurrentHashMap<>();
    private ConversationServiceImpl conversationService;

    public TextHandler(){
         conversationService = new ConversationServiceImpl();}


    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        System.out.println("payload  " + message.getPayload());
        Messages m = conversationService.processMessage(message.getPayload());
        if(userId_to_sessionId.get(m.getAuthor_id()) != null){
            userId_to_sessionId.put(m.getAuthor_id(), session.getId());
        }
        if(userId_to_sessionId.get(m.getRecipient_id()) != null){
            int sessions_id = Integer.valueOf(userId_to_sessionId.get(m.getRecipient_id()));
            WebSocketSession s = sessions.get(sessions_id);
            s.sendMessage(message);
        }

    }


    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session.getId());
        userId_to_sessionId.remove(session.getId());
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println(session + "connected");
        sessions.put(session.getId(), session);
    }
}
