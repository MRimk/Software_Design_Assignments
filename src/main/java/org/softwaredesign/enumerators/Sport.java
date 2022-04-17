package org.softwaredesign.enumerators;

public enum Sport {
    RUNNING{
        @Override
        public String toString() {
            return "Running";
        }
    },
    CYCLING{
        @Override
        public String toString() {
            return "Cycling";
        }
    },
    SWIMMING{
        @Override
        public String toString() {
            return "Swimming";
        }
    }
}
