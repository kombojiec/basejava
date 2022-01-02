package com.resume.app.model;

public enum SectionType {
    PERSONAL("Личные качества"),
    OBJECTIVE("Позиция"),
    ACHIEVEMENT("Достижения"),
    QUALIFICATIONS("Квалификация"),
    EXPERIENCE("Опыт работы"),
    EDUCATION("Образование");

    private String section;

    SectionType(String section) {
        this.section = section;
    }

    public String getSection() {
        return section;
    }
}
