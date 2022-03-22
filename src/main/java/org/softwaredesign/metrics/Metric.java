package org.softwaredesign.metrics;


import io.jenetics.jpx.GPX;
import io.jenetics.jpx.Track;
import io.jenetics.jpx.TrackSegment;
import io.jenetics.jpx.WayPoint;
import java.util.List;

public abstract class Metric {
    public abstract String display(GPX gpx); //TODO: come up with a default or exception handling
    public abstract List<Double> calculateDataPoints(GPX gpx);
    public abstract Double calculateMetricTotal(GPX gpx);
    public abstract boolean isChartable();
    public abstract boolean isUsedInGoals();
    public abstract String getMetricName();
    public abstract String getMetricUnits();
    public List<WayPoint> getWaypoints(GPX gpx){
        Track track = gpx.getTracks().get(0);
        TrackSegment segment = track.getSegments().get(0);
        return segment.getPoints();
    }
}
