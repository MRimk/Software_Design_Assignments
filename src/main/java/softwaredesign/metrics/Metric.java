package softwaredesign.metrics;


import softwaredesign.Chart;
import io.jenetics.jpx.*;

import java.util.ArrayList;

public abstract class Metric {
    public abstract String display(GPX gpx);
    abstract public ArrayList<Double> calculateDataPoints(GPX gpx);
    abstract public Double calculateMetricTotal(GPX gpx);
    abstract public Chart chartMetric(GPX gpx);
}
