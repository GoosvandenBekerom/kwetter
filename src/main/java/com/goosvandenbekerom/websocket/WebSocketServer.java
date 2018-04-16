package com.goosvandenbekerom.websocket;

import javassist.bytecode.stackmap.TypeData;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
@ServerEndpoint("/action/{username}")
public class WebSocketServer {
    private static final Logger logger = Logger.getLogger(TypeData.ClassName.class.getName());

    private Map<String, Session> sessions = new HashMap<>();

    @OnOpen
    public void open(@PathParam("username") String username, Session session, EndpointConfig conf) throws IOException {
        session.getUserProperties().put("username", username);
        sessions.put(username, session);
        session.getBasicRemote().sendText("Successfully connected to WebSocket server");
    }

    @OnClose
    public void close(@PathParam("username") String username, Session session, CloseReason reason) {
        //String username = (String) session.getUserProperties().get("username");
        sessions.remove(username);
        logger.log(Level.INFO, "Connection of " + username + "closed: " + reason.getReasonPhrase());
    }

    @OnError
    public void onError(Session session, Throwable error) {
        logger.log(Level.SEVERE, "Session: " + session.getId() + " - " + error.getMessage(), error);
    }

    @OnMessage
    public void handleMessage(String message, Session session) throws IOException {
        session.getBasicRemote().sendText("Currently incoming messages don't do anything [message: "+message+"]");
    }
}
