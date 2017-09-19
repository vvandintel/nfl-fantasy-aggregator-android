package com.vincentvandintel.fantasyaggregator.model;

/**
 * Created by vvand on 9/18/2017.
 */

public class RankedLeader {
    private String firstName;
    private String lastName;
    private String teamAbbr;
    private String opponentTeamAbbr;
    private String position;
    private Float rank;

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

    public Float getRank() {
        return rank;
    }

    public void setRank(Float rank) {
        this.rank = rank;
    }
}
