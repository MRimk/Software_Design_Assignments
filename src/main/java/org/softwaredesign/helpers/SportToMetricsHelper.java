package org.softwaredesign.helpers;

import org.softwaredesign.enumerators.Sport;
import org.softwaredesign.metrics.*;

import java.util.Map;

import static java.util.Map.entry;

public final class SportToMetricsHelper {
    private SportToMetricsHelper(){
        //this is empty since it is a static method class
    }
    private static final Map<Sport, Metric[]> metricsOfSport = Map.ofEntries(
            entry(Sport.RUNNING, new Metric[]{new Distance(), new Time(), new Elevation(), new Pace(), new Speed(), new HeartRate(), new Cadence(), new CaloriesBurnt()}),
            entry(Sport.CYCLING, new Metric[]{new Distance(), new Time(), new Speed(), new Pace(), new HeartRate(), new Power(), new CaloriesBurnt()}),
            entry(Sport.SWIMMING, new Metric[]{new Distance(), new Time(), new Pace(), new StrokeLength(), new CaloriesBurnt()}));
    public static Metric[] getSportMetrics(Sport sport){
        return metricsOfSport.get(sport);
    }
}
