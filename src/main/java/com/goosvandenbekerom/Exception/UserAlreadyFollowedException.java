package com.goosvandenbekerom.Exception;

import javax.ws.rs.WebApplicationException;

public class UserAlreadyFollowedException extends WebApplicationException {
    public UserAlreadyFollowedException() { super("You already follow the user you are trying to follow", 422); }
}
