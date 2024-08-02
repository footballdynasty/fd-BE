package com.critchlow.footballdynasty.model;

import java.util.Comparator;

public class StandingsComparator implements Comparator<Standings> {
    @Override
    public int compare(Standings s1, Standings s2) {
        return s2.wins - s1.wins;
    }
}
