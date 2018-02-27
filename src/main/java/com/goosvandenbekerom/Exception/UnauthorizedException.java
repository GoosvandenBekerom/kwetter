package com.goosvandenbekerom.Exception;

import javax.ws.rs.WebApplicationException;

public class UnauthorizedException extends WebApplicationException {
    public UnauthorizedException() { super("You are unauthorized to do this request", 401); }
}