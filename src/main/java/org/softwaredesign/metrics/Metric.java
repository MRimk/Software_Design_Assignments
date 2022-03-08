package org.softwaredesign.metrics;


import io.jenetics.jpx.GPX;
import io.jenetics.jpx.Track;
import io.jenetics.jpx.TrackSegment;
import io.jenetics.jpx.WayPoint;
import org.softwaredesign.Chart;

import java.util.ArrayList;
import java.util.List;

public abstract class Metric {
    public abstract String display(GPX gpx);
    public abstract ArrayList<Double> calculateDataPoints(GPX gpx);
    public abstract Double calculateMetricTotal(GPX gpx);
    public abstract Chart chartMetric(GPX gpx);
    public List<WayPoint> getWaypoints(GPX gpx){
        Track track = gpx.getTracks().get(0);
        TrackSegment segment = track.getSegments().get(0);
        return segment.getPoints();
    }
}
