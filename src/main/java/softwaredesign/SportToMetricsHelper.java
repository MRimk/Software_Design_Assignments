package softwaredesign;

import softwaredesign.metrics.Distance;
import softwaredesign.metrics.Metric;
import softwaredesign.metrics.Speed;
import softwaredesign.metrics.Time;

import java.util.Map;
import java.util.Vector;

import static java.util.Map.entry;

public final class SportToMetricsHelper {
    private static Map<SportTypes, Metric[]> metricsOfSport = Map.ofEntries(
            entry(SportTypes.RUNNING, new Metric[]{new Distance(), new Time()}),
            entry(SportTypes.CYCLING, new Metric[]{new Distance(), new Speed()}));
    public static Metric[] getSportMetrics(SportTypes sport){
        return metricsOfSport.get(sport);
    }
}
