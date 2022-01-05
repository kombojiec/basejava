package com.resume.app.model;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Resume implements Comparable<Resume> {

    private final String uuid;
    private String fullName;
    private Map<ContactType, String> contacts;
    private Map<SectionType, AbstractSection> sections;

    public Resume(String fullName, Map<ContactType, String> contacts, Map<SectionType, AbstractSection> sections) {
        this(fullName);
        this.contacts = contacts;
        this.sections = sections;
    }

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
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

    public Map<ContactType, String> getContacts() {
        return contacts;
    }

    public void setContacts(Map<ContactType, String> contacts) {
        this.contacts = contacts;
    }

    public Map<SectionType, AbstractSection> getSections() {
        return sections;
    }

    public void setSections(Map<SectionType, AbstractSection> sections) {
        this.sections = sections;
    }

    @Override
    public String toString() {
        StringBuilder answer = new StringBuilder();
        answer.append("Resume{\n")
                .append("uuid= " + uuid + '\n')
                .append("fullName= " + fullName + "\n\n");
        for (Map.Entry entry : contacts.entrySet()) {
            answer.append(entry.getKey()).append(": ").append(entry.getValue())
                    .append("\n");
        }
        answer.append("\n");
        for (Map.Entry<SectionType, AbstractSection> section: sections.entrySet()) {
            answer.append(section.getValue()).append("\n");
        }
        answer.append("}");
        return answer.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    @Override
    public int compareTo(Resume o) {
        return uuid.compareTo(o.uuid);
    }
}
