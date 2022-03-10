package org.softwaredesign.Helpers;

import org.softwaredesign.Gender;

import java.util.Map;
import static java.util.Map.entry;

public class StringToGenderHelper {
    private static final Map<String, Gender> stringGenderMap = Map.ofEntries(
            entry("M", Gender.MALE),
            entry("F", Gender.FEMALE)
    );
    public static Gender getGender(String gender){
        return stringGenderMap.get(gender);
    }
}
