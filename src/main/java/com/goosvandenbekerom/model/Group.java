package com.goosvandenbekerom.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "UserGroup")
public class Group {
    @Id
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<User> users;

    public Group() { this.users = new ArrayList<>(); }
    public Group(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
