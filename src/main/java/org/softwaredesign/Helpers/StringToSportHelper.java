package org.softwaredesign.Helpers;

import org.softwaredesign.Sport;

import java.util.Map;

import static java.util.Map.entry;

public class StringToSportHelper {
    private static final Map<String, Sport> stringSportMap = Map.ofEntries(
            entry("running", Sport.RUNNING),
            entry("swimming", Sport.SWIMMING),
            entry("cycling", Sport.CYCLING)
    );
    public static Sport getSport(String sport){
        return stringSportMap.get(sport);
    }
}
