package softwaredesign.metrics;


import softwaredesign.Chart;
import org.alternativevision.gpx.beans.GPX;

import java.util.ArrayList;

public interface Metric {
    static String display(GPX gpx){
        return "";
    }
    static ArrayList<Double> calculateDataPoints(GPX gpx) {
        return new ArrayList<>();
    }
    static Double calculateMetricTotal(GPX gpx) {
        return 0.0;
    }
    static Chart chartMetric(GPX gpx) {
        return new Chart();
    }
}
