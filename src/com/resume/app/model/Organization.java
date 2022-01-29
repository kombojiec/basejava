package com.resume.app.model;

import com.google.gson.annotations.JsonAdapter;
import com.resume.app.util.GsonLocalDateTimeAdapter;
import com.resume.app.util.LocalDateAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;
    private String title;
    private Link link;
    private List<Position> positions = new ArrayList<>();

    public Organization() {
    }

    public Organization(String title, String URL, List<Position> positions) {
        this.link = new Link(title, URL);
        this.title = title;
        this.positions = positions;
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
        for (Position position : positions) {
            answer.append(position);
            answer.append(positions.size() > 1 ? "\n" : "");
        }
        return answer.toString();
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Position implements Serializable {
        private static final long serialVersionUID = 1L;
        private String description;
        @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
        @JsonAdapter(value = GsonLocalDateTimeAdapter.class)
        private LocalDate startDate;
        @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
        @JsonAdapter(value = GsonLocalDateTimeAdapter.class)
        private LocalDate endDate;

        public Position() {
        }

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
            return this.endDate;
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Position)) return false;
            Position position = (Position) o;
            return Objects.equals(getDescription(), position.getDescription())
                    && Objects.equals(getStartDate(), position.getStartDate())
                    && Objects.equals(getEndDate(), position.getEndDate());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getDescription(), getStartDate(), getEndDate());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Organization)) return false;
        Organization that = (Organization) o;
        return Objects.equals(getTitle(), that.getTitle())
                && Objects.equals(getLink(), that.getLink())
                && Objects.equals(getPositions(), that.getPositions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getLink(), getPositions());
    }
}
