package com.goosvandenbekerom.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Hashtag {
    @Id
    private String value;
    private int count;

    @ManyToMany(mappedBy = "hashtags")
    private List<Kweet> kweets;

    public Hashtag() {}
    public Hashtag(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void increaseCount() {
        count++;
    }
}
