package org.softwaredesign.enumerators;

public enum Gender {
    MALE{
        @Override
        public String toString() {
            return "M";
        }
    },
    FEMALE{
        @Override
        public String toString(){
            return "F";
        }
    }
}
