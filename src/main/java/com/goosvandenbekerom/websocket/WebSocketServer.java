package com.goosvandenbekerom.websocket;

import javassist.bytecode.stackmap.TypeData;

import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@ServerEndpoint(value = "/action/{username}", encoders = {KweetJsonEncoder.class})
public class WebSocketServer {
    private static final Logger logger = Logger.getLogger(TypeData.ClassName.class.getName());

    @Inject
    private WebSocketConfig config;

    @OnOpen
    public void open(@PathParam("username") String username, Session session, EndpointConfig conf) {
        config.addSession(username, session);
    }

    @OnClose
    public void close(@PathParam("username") String username, Session session, CloseReason reason) {
        config.removeSession(username);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        logger.log(Level.SEVERE, "Session: " + session.getId() + " - " + error.getMessage(), error);
    }
}
