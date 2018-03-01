package com.goosvandenbekerom.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;
import java.util.Objects;

@Entity
public class Hashtag {
    @Id
    private String value;
    private int count = 0;

    @ManyToMany(mappedBy = "hashtags")
    private List<Kweet> kweets;

    /**
     * When instantiating a hashtag from code, always use Hashtagservice.create().
     * this constructor is only here for JPA
     */
    public Hashtag() {}
    /**
     * When instantiating a hashtag from code, always use Hashtagservice.create().
     * this constructor is only here for JPA
     */
    public Hashtag(String tag) {
        value = tag;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hashtag)) return false;
        Hashtag hashtag = (Hashtag) o;
        return getCount() == hashtag.getCount() &&
                Objects.equals(getValue(), hashtag.getValue()) &&
                Objects.equals(kweets, hashtag.kweets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue(), getCount(), kweets);
    }
}
