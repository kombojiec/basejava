package com.resume.app.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class OrganizationSection extends AbstractSection {
    private static final long serialVersionUID = 1L;
    private List<Organization> organizations;

    public OrganizationSection() {
    }

    public OrganizationSection(List<Organization> organizations) {
        this.organizations = organizations;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }

    @Override
    public String toString() {
        StringBuilder answer = new StringBuilder();
        for (Organization organization : organizations) {
            answer.append(organization).append("\n");
        }
        return answer.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrganizationSection)) return false;
        OrganizationSection that = (OrganizationSection) o;
        return Objects.equals(getOrganizations(), that.getOrganizations());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrganizations());
    }
}
