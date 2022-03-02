package softwaredesign;

import softwaredesign.metrics.Metric;

import java.util.Map;
import java.util.Vector;

public final class SportToMetricsHelper {
    private Map<SportTypes, Vector<Metric>> metricsOfSport;
    public static Vector<Metric> getSportMetrics(SportTypes sport){
        return new Vector<Metric>();
    }
}
