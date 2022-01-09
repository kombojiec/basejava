package com.resume.app.model;

import java.util.List;

public class OrganizationSection extends AbstractSection {
    private List<Organization> organizations;

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
}
