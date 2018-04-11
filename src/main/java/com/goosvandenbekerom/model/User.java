package com.goosvandenbekerom.model;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries(
        @NamedQuery(name = "user.getTimeline",
                query = "SELECT k FROM Kweet k, User u WHERE u.username = :username "+
                        "AND (k.owner.username in (SELECT f.username from u.following f) "+
                        "OR k.owner.username = :username) ORDER BY k.created desc")
)
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

    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Group> groups;

    @JsonbDateFormat("yyyy-MM-dd'T'HH:mm'Z'")
    private Date created;
    @JsonbDateFormat("yyyy-MM-dd'T'HH:mm'Z'")
    private Date updated;
    @PrePersist
    protected void onCreate() {
        created = updated = new Date();
    }
    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }

    public User() { }
    public User(String username, String password, String fullName) {
        this();
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        following = new ArrayList<>();
        followers = new ArrayList<>();
        groups = new ArrayList<>();
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

    public List<User> getFollowing() {
        if (following == null) {
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

    public List<Group> getGroups() {
        if (groups == null) {
            groups = new ArrayList<>();
        }
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getUsername(), user.getUsername()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getFullName(), user.getFullName()) &&
                Objects.equals(getCreated(), user.getCreated());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword(), getFullName(), getCreated());
    }
}