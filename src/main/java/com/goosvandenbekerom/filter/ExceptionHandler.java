package com.goosvandenbekerom.filter;

import com.goosvandenbekerom.model.ErrorResponse;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ExceptionHandler implements ExceptionMapper<WebApplicationException> {
    @Override
    public Response toResponse(WebApplicationException e) {
        int status = e.getResponse().getStatus();
        ErrorResponse entity = new ErrorResponse(status, e.getMessage());

        return Response.status(e.getResponse().getStatus())
                .entity(entity)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
