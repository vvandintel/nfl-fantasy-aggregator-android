package com.vincentvandintel.fantasyaggregator.model;

/**
 * Created by vvand on 9/24/2017.
 */

public abstract class Leader {
    private String firstName;
    private String lastName;
    private String teamAbbr;
    private String opponentTeamAbbr;
    private String position;

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

    public String getTeamAbbr() {
        return teamAbbr;
    }

    public void setTeamAbbr(String teamAbbr) {
        this.teamAbbr = teamAbbr;
    }

    public String getOpponentTeamAbbr() {
        return opponentTeamAbbr;
    }

    public void setOpponentTeamAbbr(String opponentTeamAbbr) {
        this.opponentTeamAbbr = opponentTeamAbbr;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
