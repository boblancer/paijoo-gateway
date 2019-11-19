package com.example.producer.encoder;

import com.example.producer.models.database.Messages;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class MessageEncoder implements Encoder.Text<Messages> {
    @Override
    public void init(EndpointConfig ec) { }
    @Override
    public void destroy() { }
    @Override
    public String encode(Messages message) throws EncodeException {
        // Access msgA's properties and convert to JSON text...
        String result = "";

        return result;
    }
}