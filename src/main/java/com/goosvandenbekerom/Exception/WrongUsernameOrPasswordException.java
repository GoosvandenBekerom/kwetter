package com.goosvandenbekerom.Exception;

import javax.ws.rs.WebApplicationException;

public class WrongUsernameOrPasswordException extends WebApplicationException {
    public WrongUsernameOrPasswordException() { super("The username or password you entered is incorrect", 403); }
}