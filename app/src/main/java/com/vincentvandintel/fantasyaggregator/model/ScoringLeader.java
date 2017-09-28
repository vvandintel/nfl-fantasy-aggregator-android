package com.vincentvandintel.fantasyaggregator.model;

/**
 * Created by vvand on 9/11/2017.
 */

public class ScoringLeader extends Leader {
    private String statsLine;
    private String pts;
    private String projectedPts;
    private String playerId;

    public ScoringLeader() {
        super();
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
}
