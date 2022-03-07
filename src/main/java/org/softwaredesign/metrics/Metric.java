package org.softwaredesign.metrics;


import io.jenetics.jpx.GPX;
import org.softwaredesign.Chart;

import java.util.ArrayList;

public abstract class Metric {
    public abstract String display(GPX gpx);
    public abstract ArrayList<Double> calculateDataPoints(GPX gpx);
    public abstract Double calculateMetricTotal(GPX gpx);
    public abstract Chart chartMetric(GPX gpx);
}
