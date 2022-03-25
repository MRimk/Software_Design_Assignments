package org.softwaredesign.helpers;

import org.softwaredesign.enumerators.Gender;
import org.softwaredesign.enumerators.Sport;

import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public final class StringToSportHelper {
    private StringToSportHelper(){
        //this is empty since it is a static method class
    }
//    private static final Map<String, Sport> stringSportMap = Map.ofEntries(
//            entry(Sport.RUNNING.toString(), Sport.RUNNING),
//            entry(Sport.SWIMMING.toString(), Sport.SWIMMING),
//            entry(Sport.CYCLING.toString(), Sport.CYCLING)
//    );
    public static Sport getSport(String sportString){
        Map<String, Sport> sportsMap = new HashMap<>();
        for(Sport sport : Sport.values()){
            sportsMap.put(sport.toString(), sport);
        }
        return sportsMap.get(sportString);
    }
}
