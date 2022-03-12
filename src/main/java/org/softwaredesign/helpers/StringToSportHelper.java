package org.softwaredesign.helpers;

import org.softwaredesign.enumerators.Sport;

import java.util.Map;

import static java.util.Map.entry;

public final class StringToSportHelper {
    private StringToSportHelper(){
        //this is empty since it is a static method class
    }
    private static final Map<String, Sport> stringSportMap = Map.ofEntries(
            entry("running", Sport.RUNNING),
            entry("swimming", Sport.SWIMMING),
            entry("cycling", Sport.CYCLING)
    );
    public static Sport getSport(String sport){
        return stringSportMap.get(sport);
    }
}
