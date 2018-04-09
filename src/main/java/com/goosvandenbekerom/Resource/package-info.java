@OpenAPIDefinition(
    info = @Info(
        title = "Kwetter API",
        version = "1.0.-SNAPSHOT",
        description = "API to communicate with Kwetter backend"
    ),
    servers = {
        @Server(description = "local", url = "http://localhost:8080/kwetter/api")
    }
)
package com.goosvandenbekerom.Resource;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;