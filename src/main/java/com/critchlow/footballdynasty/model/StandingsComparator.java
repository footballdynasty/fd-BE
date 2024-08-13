package com.critchlow.footballdynasty.model;

import java.util.Comparator;

public class StandingsComparator implements Comparator<Standings> {
    @Override
    public int compare(Standings s1, Standings s2) {
        int winComparison = s2.conference_wins - s1.conference_wins;
        int lossComparison = s2.conference_losses - s1.conference_losses;
        return winComparison - lossComparison;
    }
}
