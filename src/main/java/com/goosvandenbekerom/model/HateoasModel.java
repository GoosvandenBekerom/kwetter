package com.goosvandenbekerom.model;

import javax.persistence.Transient;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

abstract class HateoasModel {
    @Transient
    private List<Link> links;

    public List<Link> getLinks() {
        if (links == null) {
            links = new ArrayList<>();
        }
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public void addLink(String rel, URI uri) {
        getLinks().add(new Link(rel, uri.toASCIIString()));
    }
}
