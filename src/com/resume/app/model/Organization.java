package com.resume.app.model;

import java.time.LocalDate;

public class Organization {
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Link link;

    public Organization(String title, String description, LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    @Override
    public String toString() {
        StringBuilder answer = new StringBuilder();
        answer.append("Title: ").append(title).append("\n");
        if (link != null) {
            answer.append("Home page: ").append(link).append("\n");
        }
        answer.append("Date: ").append(startDate).append(" - ").append(endDate).append("\n")
                .append("Description: ").append(description).append("\n");
        return answer.toString();
    }
}
