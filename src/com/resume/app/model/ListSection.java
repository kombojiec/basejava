package com.resume.app.model;

import java.util.List;

public class ListSection extends AbstractSection {
    private List<String> elements;

    public ListSection(SectionType section, List<String> elements) {
        super(section);
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
        answer.append(getSection().getSection()).append("\n");
        for (String element : elements) {
            answer.append("- ").append(element).append("\n");
        }
        return answer.toString();
    }
}
