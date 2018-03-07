package com.goosvandenbekerom.Resource;

import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Produces(MediaType.APPLICATION_JSON)
abstract class JsonResource {
    @Context
    UriInfo uri;
}
