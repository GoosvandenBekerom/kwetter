package com.goosvandenbekerom.Exception;

import javax.ws.rs.ForbiddenException;

public class WrongUsernameOrPasswordException extends ForbiddenException {
    public WrongUsernameOrPasswordException() { super("The combination of username and password you entered is incorrect"); }
}