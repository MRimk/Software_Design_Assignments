package softwaredesign.metrics;


import softwaredesign.Chart;

import java.util.ArrayList;

public interface Metric {
    static String display(){
        return "";
    }
    private static ArrayList<Double> calculateDataPoints() {
        return new ArrayList<Double>();
    }
    private static Double calculateMetricTotal() {
        return 0.0;
    }
    static Chart chartMetric() {
        return new Chart();
    }
}
