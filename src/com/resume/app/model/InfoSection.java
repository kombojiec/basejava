package com.resume.app.model;

public class InfoSection extends AbstractSection {
    private String content;

    public InfoSection(SectionType section, String content) {
        super(section);
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

        return SectionType.valueOf(getSection().toString()).getSection() + "\n" + content + "\n";
    }
}
