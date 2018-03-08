package com.goosvandenbekerom.model;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.goosvandenbekerom.config.JwtConfig;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class User extends HateoasModel {

    @Id
    private String username;
    @JsonbTransient
    private String password;
    private String fullName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonbTransient
    private List<User> following;

    @ManyToMany(mappedBy = "following",fetch = FetchType.LAZY)
    @JsonbTransient
    private List<User> followers;

    private Date created;
    @PrePersist
    protected void onCreate() {
        created = new Date();
    }

    public User() {}

    public User(String username, String password, String fullName) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        following = new ArrayList<>();
        followers = new ArrayList<>();
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public List<User> getFollowing() {
        if (following == null){
            following = new ArrayList<>();
        }
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    public List<User> getFollowers() {
        if (followers == null) {
            followers = new ArrayList<>();
        }
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getUsername(), user.getUsername()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getFullName(), user.getFullName()) &&
                Objects.equals(getFollowing(), user.getFollowing()) &&
                Objects.equals(getCreated(), user.getCreated());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword(), getFullName(), getFollowing(), getCreated());
    }
}