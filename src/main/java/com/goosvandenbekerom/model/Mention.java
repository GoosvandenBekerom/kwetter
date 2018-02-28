package com.goosvandenbekerom.model;

import javax.persistence.*;

@Entity
public class Mention {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private User user;

    public Mention() {}
    public Mention(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User mentionedUser) {
        this.user = mentionedUser;
    }
}
