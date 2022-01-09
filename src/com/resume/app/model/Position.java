package com.resume.app.model;

import java.time.LocalDate;

public class Position {
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

    public Position(String description, LocalDate startDate, LocalDate endDate) {
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
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

    @Override
    public String toString() {
        StringBuilder answer = new StringBuilder();
        answer.append("\tDate: ").append(startDate).append(" - ").append(endDate).append("\n")
                .append("\tDescription: ").append(description)
                .append("\n");
        return answer.toString();
    }
}
