package org.softwaredesign.Helpers;

import org.softwaredesign.SportTypes;
import org.softwaredesign.metrics.*;

import java.util.Map;

import static java.util.Map.entry;

public final class SportToMetricsHelper {
    private static final Map<SportTypes, Metric[]> metricsOfSport = Map.ofEntries(
            entry(SportTypes.RUNNING, new Metric[]{new Distance(), new Time(), new Elevation(), new Pace(), new HeartRate(), new Cadence()}),
            entry(SportTypes.CYCLING, new Metric[]{new Distance(), new Time(), new Speed(), new HeartRate(), new Power()}),
            entry(SportTypes.SWIMMING, new Metric[]{new Distance(), new Time(), new Pace(), new StrokeLength(), new CaloriesBurnt()}));
    public static Metric[] getSportMetrics(SportTypes sport){
        return metricsOfSport.get(sport);
    }
}