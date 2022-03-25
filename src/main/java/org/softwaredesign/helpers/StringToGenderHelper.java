package org.softwaredesign.helpers;

import org.softwaredesign.enumerators.Gender;

import java.util.HashMap;
import java.util.Map;
import static java.util.Map.entry;

public final class StringToGenderHelper {
    private StringToGenderHelper(){
        //this is empty since it is a static method class
    }
//    private static final Map<String, Gender> stringGenderMap = Map.ofEntries(
//            entry(Gender.MALE.toString(), Gender.MALE),
//            entry(Gender.FEMALE.toString(), Gender.FEMALE)
//    );
    public static Gender getGender(String genderString){
        Map<String, Gender> genderMap = new HashMap<>();
        for(Gender gender : Gender.values()){
            genderMap.put(gender.toString(), gender);
        }
        return genderMap.get(genderString);
    }
}
