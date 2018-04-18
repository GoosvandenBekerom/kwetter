package com.goosvandenbekerom.websocket;

import com.goosvandenbekerom.model.Kweet;
import javassist.bytecode.stackmap.TypeData;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class WebSocketConfig {
    private static final Logger logger = Logger.getLogger(TypeData.ClassName.class.getName());

    private Map<String, Session> sessions = new HashMap<>();

    public boolean sessionExists(String username) {
        return sessions.containsKey(username);
    }

    public Session getSession(String username) {
        if (!sessionExists(username)) return null;
        return sessions.get(username);
    }

    public void addSession(String username, Session session) {
        sessions.put(username, session);
    }

    public void removeSession(String username) {
        sessions.remove(username);
    }

    public void sendKweet(Kweet kweet, String username) {
        Session session = getSession(username);
        if (session == null) return;

        try {
            session.getBasicRemote().sendObject(kweet);
        } catch (IOException | EncodeException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
