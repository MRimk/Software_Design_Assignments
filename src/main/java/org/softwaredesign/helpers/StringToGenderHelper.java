package org.softwaredesign.helpers;

import org.softwaredesign.enumerators.Gender;

import java.util.HashMap;
import java.util.Map;

public final class StringToGenderHelper {
    private StringToGenderHelper(){
        //this is empty since it is a static method class
    }

    /**
     * A converter function that given a string returns the correct type of gender.
     * @param genderString
     * String containing abbreviation of the gender
     * @return
     * Gender enumerator object
     */
    public static Gender getGender(String genderString){
        Map<String, Gender> genderMap = new HashMap<>();
        for(Gender gender : Gender.values()){
            genderMap.put(gender.toString(), gender);
        }
        return genderMap.get(genderString);
    }
}
