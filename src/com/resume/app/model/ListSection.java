package com.resume.app.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class ListSection extends AbstractSection implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<String> elements;

    public ListSection(List<String> elements) {
        this.elements = elements;
    }

    public List<String> getElements() {
        return elements;
    }

    public void setElements(List<String> elements) {
        this.elements = elements;
    }

    @Override
    public String toString() {
        StringBuilder answer = new StringBuilder();
        for (String element : elements) {
            answer.append("- ").append(element).append("\n");
        }
        return answer.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListSection)) return false;
        ListSection that = (ListSection) o;
        return Objects.equals(getElements(), that.getElements());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getElements());
    }
}
