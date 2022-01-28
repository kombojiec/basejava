package com.resume.app.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class SimpleLineSection extends AbstractSection {
    private static final long serialVersionUID = 1L;
    private String content;

    public SimpleLineSection() {
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SimpleLineSection)) return false;
        SimpleLineSection that = (SimpleLineSection) o;
        return Objects.equals(getContent(), that.getContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getContent());
    }
}
