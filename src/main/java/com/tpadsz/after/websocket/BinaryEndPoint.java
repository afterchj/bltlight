package com.tpadsz.after.websocket;

import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;

/**
 * Created by hongjian.chen on 2018/8/1.
 */
public class BinaryEndPoint extends BinaryWebSocketHandler {
    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
//        byte[] buffer = message.getPayload().array();
        System.out.println(String.format("[BinaryWebSocketHandler] user:%s, got binary message", session.getId()));
        super.handleBinaryMessage(session, message);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("[BinaryWebSocketHandler] connection established, id=" + session.getId());
        super.afterConnectionEstablished(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("[BinaryWebSocketHandler] connection closed, id=" + session.getId());
        super.afterConnectionClosed(session, status);
    }
}
