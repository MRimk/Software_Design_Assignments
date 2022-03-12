package org.softwaredesign.helpers;

import org.softwaredesign.enumerators.Gender;

import java.util.Map;
import static java.util.Map.entry;

public final class StringToGenderHelper {
    private StringToGenderHelper(){
        //this is empty since it is a static method class
    }
    private static final Map<String, Gender> stringGenderMap = Map.ofEntries(
            entry("M", Gender.MALE),
            entry("F", Gender.FEMALE)
    );
    public static Gender getGender(String gender){
        return stringGenderMap.get(gender);
    }
}
