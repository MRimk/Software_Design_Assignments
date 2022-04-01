package org.softwaredesign.helpers;

import org.softwaredesign.enumerators.Sport;

import java.util.HashMap;
import java.util.Map;

public final class StringToSportHelper {
    private StringToSportHelper(){
        //this is empty since it is a static method class
    }

    /**
     * A converter function that given a string returns the correct type of sport.
     * @param sportString
     * String containing the name of the sport
     * @return
     * Sport enumerator object indicating the type of sport
     */
    public static Sport getSport(String sportString){
        Map<String, Sport> sportsMap = new HashMap<>();
        for(Sport sport : Sport.values()){
            sportsMap.put(sport.toString(), sport);
        }
        return sportsMap.get(sportString);
    }
}
