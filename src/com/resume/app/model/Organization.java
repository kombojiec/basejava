package com.resume.app.model;

import java.util.ArrayList;
import java.util.List;

public class Organization {
    private String title;
    private Link link;
    private List<Position> positions = new ArrayList<>();

    public Organization(String title, Position position) {
        this.title = title;
        this.positions.add(position);
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
}
