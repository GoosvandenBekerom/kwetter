package com.goosvandenbekerom.websocket;

import com.goosvandenbekerom.model.Kweet;

import javax.json.bind.JsonbBuilder;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class KweetJsonEncoder implements Encoder.Text<Kweet> {
    @Override
    public String encode(Kweet kweet) {
        return JsonbBuilder.create().toJson(kweet);
    }

    @Override public void init(EndpointConfig config) {}
    @Override public void destroy() {}
}
