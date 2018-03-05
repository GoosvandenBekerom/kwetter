package com.goosvandenbekerom.Exception;

import javax.ws.rs.WebApplicationException;

public class UnknownUserException extends WebApplicationException {
    public UnknownUserException() { super("Resource not found: Unknown username", 404); }
}
