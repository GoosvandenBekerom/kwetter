package com.goosvandenbekerom.model;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.goosvandenbekerom.config.JwtConfig;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.UnsupportedEncodingException;

@Entity
public class User {

    @Id
    private String username;
    private String password;
    private String fullName;

    public User() {}

    public User(String username, String password, String fullName) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
    }

    public Token generateToken() {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JwtConfig.SECRET);
            return new Token(JWT.create()
                    .withClaim("user", username)
                    .withIssuer("auth0")
                    .sign(algorithm));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}