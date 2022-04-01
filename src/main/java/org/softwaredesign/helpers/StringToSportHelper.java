package org.softwaredesign.helpers;

import org.softwaredesign.enumerators.Sport;

import java.util.HashMap;
import java.util.Map;

public final class StringToSportHelper {
    private StringToSportHelper(){
        //this is empty since it is a static method class
    }

    public static Sport getSport(String sportString){
        Map<String, Sport> sportsMap = new HashMap<>();
        for(Sport sport : Sport.values()){
            sportsMap.put(sport.toString(), sport);
        }
        return sportsMap.get(sportString);
    }
}
