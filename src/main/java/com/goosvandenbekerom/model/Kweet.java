package com.goosvandenbekerom.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Kweet {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private User owner;

    private String message;
    private int likeCount;

    public Kweet() {}
    public Kweet(User owner, String message) {
        this.owner = owner;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int addLike() {
        return ++this.likeCount;
    }
}