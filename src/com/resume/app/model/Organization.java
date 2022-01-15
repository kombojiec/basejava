package com.resume.app.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;
    private String title;
    private Link link;
    private List<Position> positions = new ArrayList<>();

    public Organization(String title, String URL, List<Position> positions) {
        this.link = new Link(title, URL);
        this.title = title;
        this.positions  = positions;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPosition(Position position) {
        this.positions.add(position);
    }

    @Override
    public String toString() {
        StringBuilder answer = new StringBuilder();
        answer.append("Title: ").append(title).append("\n");
        if (link != null) {
            answer.append("Home page: ").append(link).append("\n");
        }
        for (Position position: positions) {
            answer.append(position);
            answer.append(positions.size() > 1? "\n": "");
        }
        return answer.toString();
    }

    public static class Position implements Serializable{
        private static final long serialVersionUID = 1L;
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
}
