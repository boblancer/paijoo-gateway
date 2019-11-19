package com.example.producer.configs;

import com.example.producer.handlers.TextHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    TextHandler textHandler;
    @Autowired
    ApplicationContext ctx;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(textHandler, "/conversation/*")
                .addInterceptors(idInterceptor());;
    }
    @Bean
    public HandshakeInterceptor idInterceptor() {
        return new HandshakeInterceptor() {

            @Override
            public boolean beforeHandshake(org.springframework.http.server.ServerHttpRequest serverHttpRequest
                    , org.springframework.http.server.ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler
                    , Map<String, Object> map) throws Exception {
                // Get the URI segment corresponding to the auction id during handshake
                String path = serverHttpRequest.getURI().getPath();
                String id = path.substring(path.lastIndexOf('/') + 1);
                System.out.println("Id input " + id);
                TextHandler t = ctx.getBean(TextHandler.class);

                // This will be added to the websocket session
                map.put("id", id);
                return true;
            }

            @Override
            public void afterHandshake(org.springframework.http.server.ServerHttpRequest serverHttpRequest, org.springframework.http.server.ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {

            }

        };
    }
}

