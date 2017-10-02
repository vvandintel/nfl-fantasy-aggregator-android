package com.vincentvandintel.fantasyaggregator.model;

/**
 * Created by vvand on 9/25/2017.
 */

public class PlayerNewsItem {
    private String gsisPlayerId;
    private String firstName;
    private String lastName;
    private String position;
    private String teamAbbr;
    private String timestamp;
    private String body;
    private String analysis;

    public String getGsisPlayerId() {
        return gsisPlayerId;
    }

    public void setGsisPlayerId(String gsisPlayerId) {
        this.gsisPlayerId = gsisPlayerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTeamAbbr() {
        return teamAbbr;
    }

    public void setTeamAbbr(String teamAbbr) {
        this.teamAbbr = teamAbbr;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }
}
