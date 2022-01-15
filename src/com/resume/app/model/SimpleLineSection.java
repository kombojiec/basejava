package com.resume.app.model;

import java.io.Serializable;

public class SimpleLineSection extends AbstractSection implements Serializable {
    private static final long serialVersionUID = 1L;
    private String content;

    public SimpleLineSection(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return content + "\n";
    }
}
