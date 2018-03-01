package com.goosvandenbekerom.model;

import javax.persistence.*;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof Mention
                && hashCode() == o.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser());
    }
}
