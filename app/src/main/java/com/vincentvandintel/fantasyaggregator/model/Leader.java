package com.vincentvandintel.fantasyaggregator.model;

/**
 * Created by vvand on 9/11/2017.
 */

public class Leader {
    private String firstName;

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

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getStatsLine() {
        return statsLine;
    }

    public void setStatsLine(String statsLine) {
        this.statsLine = statsLine;
    }

    public String getPts() {
        return pts;
    }

    public void setPts(String pts) {
        this.pts = pts;
    }

    public String getProjectedPts() {
        return projectedPts;
    }

    public void setProjectedPts(String projectedPts) {
        this.projectedPts = projectedPts;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    private String lastName;
    private String position;
    private int rank;
    private String statsLine;
    private String pts;
    private String projectedPts;
    private String playerId;

    public Leader() {
    }
}
