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
            entry(Sport.RUNNING, new Metric[]{
                    Distance.getInstance(),
                    Time.getInstance(),
                    Elevation.getInstance(),
                    Pace.getInstance(),
                    HeartRate.getInstance(),
                    Cadence.getInstance(),
                    CaloriesBurnt.getInstance()}),
            entry(Sport.CYCLING, new Metric[]{
                    Distance.getInstance(),
                    Time.getInstance(),
                    Speed.getInstance(),
                    HeartRate.getInstance(),
                    Power.getInstance(),
                    CaloriesBurnt.getInstance()}),
            entry(Sport.SWIMMING, new Metric[]{
                    Distance.getInstance(),
                    Time.getInstance(),
                    Pace.getInstance(),
                    StrokeLength.getInstance(),
                    CaloriesBurnt.getInstance()}));
    public static Metric[] getSportMetrics(Sport sport){
        return metricsOfSport.get(sport);
    }
}
