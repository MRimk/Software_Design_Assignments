package org.softwaredesign.metrics;


import io.jenetics.jpx.GPX;
import org.softwaredesign.Chart;

import java.util.ArrayList;

public abstract class Metric {
    public abstract String display(GPX gpx);
    abstract public ArrayList<Double> calculateDataPoints(GPX gpx);
    abstract public Double calculateMetricTotal(GPX gpx);
    abstract public Chart chartMetric(GPX gpx);
}
