package com.critchlow.footballdynasty.model;

import java.util.Comparator;

public class StandingsComparator implements Comparator<Standings> {
    @Override
    public int compare(Standings s1, Standings s2) {
        int winComparison = s2.wins - s1.wins;
        int lossComparison = s2.losses - s1.losses;
        return winComparison - lossComparison;
    }
}
