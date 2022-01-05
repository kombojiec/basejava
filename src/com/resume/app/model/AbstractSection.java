package com.resume.app.model;

public abstract class AbstractSection {
    private SectionType section;

    protected AbstractSection(SectionType section) {
        this.section = section;
    }

    protected SectionType getSection() {
        return section;
    }

    protected void setSection(SectionType section) {
        this.section = section;
    }
}
