package org.softwaredesign.Helpers;

import org.softwaredesign.SportTypes;

import java.util.Map;

import static java.util.Map.entry;

public class StringToSportHelper {
    private static final Map<String, SportTypes> stringSportMap = Map.ofEntries(
            entry("running", SportTypes.RUNNING),
            entry("swimming", SportTypes.SWIMMING),
            entry("cycling", SportTypes.CYCLING)
    );
    public static SportTypes getSport(String sport){
        return stringSportMap.get(sport);
    }
}
