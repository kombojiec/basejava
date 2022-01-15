package com.resume.app.model;

import java.util.Objects;

public class Link {
    private String name;
    private String url;

    public Link(String name, String url) {
        Objects.requireNonNull(name, "name must not be null");
        this.name = name;
        this.url = url;
    }

    @Override
    public String toString() {
        return  url == null? "--": url;
    }
}
