package com.goosvandenbekerom.resource;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Produces(MediaType.APPLICATION_JSON)
abstract class JsonResource {}
