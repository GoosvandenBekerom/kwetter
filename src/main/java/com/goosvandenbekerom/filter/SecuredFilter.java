package com.goosvandenbekerom.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.goosvandenbekerom.Exception.UnauthorizedException;
import com.goosvandenbekerom.annotation.Secured;
import com.goosvandenbekerom.config.JwtConfig;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

@Secured
@Provider
public class SecuredFilter implements ContainerRequestFilter {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String AUTHORIZATION_HEADER_PREFIX = "Bearer ";

    @Override
    public void filter(ContainerRequestContext requestContext) {
        String authHeader = requestContext.getHeaderString(AUTHORIZATION_HEADER);
        String authToken = authHeader != null ? authHeader.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "") : "";

        if (!verifyToken(authToken, requestContext))
            throw new UnauthorizedException();
    }

    private Boolean verifyToken(String token, ContainerRequestContext requestContext) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JwtConfig.SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build();

            DecodedJWT jwt = verifier.verify(token);
            requestContext.setProperty("user", jwt.getClaim("user").asString());

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}