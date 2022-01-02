package com.resume.app.model;

public abstract class Section {
    private SectionType section;

    protected Section(SectionType section) {
        this.section = section;
    }

    protected SectionType getSection() {
        return section;
    }

    protected void setSection(SectionType section) {
        this.section = section;
    }
}
