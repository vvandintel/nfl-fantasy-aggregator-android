package com.vincentvandintel.fantasyaggregator.model;

/**
 * Created by vvand on 9/18/2017.
 */

public class RankedLeader extends Leader {
    public RankedLeader() {
        super();
    }

    public Float getRank() {
        return rank;
    }

    public void setRank(Float rank) {
        this.rank = rank;
    }

    private Float rank;
}
