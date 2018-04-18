package com.goosvandenbekerom.websocket;

import com.goosvandenbekerom.model.Kweet;
import com.goosvandenbekerom.model.User;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.io.Serializable;

@ApplicationScoped
public class KweetEventReceiver implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private WebSocketConfig config;

    void receiveAsync(@Observes Kweet kweet) {
        for (User follower : kweet.getOwner().getFollowers()) {
            config.sendKweet(kweet, follower.getUsername());
        }
    }
}
