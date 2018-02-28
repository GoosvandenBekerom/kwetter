package com.goosvandenbekerom.Exception;

import javax.ws.rs.WebApplicationException;

public class UsernameExistsException extends WebApplicationException {
    public UsernameExistsException() { super("The username you entered already exists", 422); }
}
