package org.softwaredesign;

import org.softwaredesign.metrics.Distance;
import org.softwaredesign.metrics.Metric;
import org.softwaredesign.metrics.Speed;
import org.softwaredesign.metrics.Time;

import java.util.Map;

import static java.util.Map.entry;

public final class SportToMetricsHelper {
    private static Map<SportTypes, Metric[]> metricsOfSport = Map.ofEntries(
            entry(SportTypes.RUNNING, new Metric[]{new Distance(), new Time()}),
            entry(SportTypes.CYCLING, new Metric[]{new Distance(), new Speed()}));
    public static Metric[] getSportMetrics(SportTypes sport){
        return metricsOfSport.get(sport);
    }
}
