package com.resume.app.model;

import java.util.*;

public class Resume implements Comparable<Resume> {

    private final String uuid;
    private String fullName;
    private Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
    private Map<SectionType, AbstractSection> sections = new EnumMap<>(SectionType.class);

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getContact(ContactType type) {
        return contacts.get(type);
    }

    public void setContact(ContactType type, String value) {
        contacts.put(type, value);
    }

    public AbstractSection getSection(SectionType type) {
        return sections.get(type);
    }

    public void setSection(SectionType type, AbstractSection section) {
        sections.put(type, section);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return uuid.equals(resume.uuid) && fullName.equals(resume.fullName) && contacts.equals(resume.contacts)
                && sections.equals(resume.sections);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName, contacts, sections);
    }

    @Override
    public int compareTo(Resume o) {
        return uuid.compareTo(o.uuid);
    }

    @Override
    public String toString() {
        StringBuilder answer = new StringBuilder();
        answer.append("Resume{\n")
                .append("uuid = " + uuid + '\n')
                .append("Full name = " + fullName + "\n\n");
        for (Map.Entry entry : contacts.entrySet()) {
            answer.append(entry.getKey()).append(": ").append(entry.getValue())
                    .append("\n");
        }
        answer.append("\n");
        for (Map.Entry<SectionType, AbstractSection> section : sections.entrySet()) {
            answer.append(section.getKey().getSection()).append("\n")
                    .append(section.getValue()).append("\n");
        }
        answer.append("}");
        return answer.toString();
    }

}
