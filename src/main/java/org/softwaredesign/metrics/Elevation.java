package org.softwaredesign.metrics;

import io.jenetics.jpx.*;
import org.softwaredesign.Chart;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Elevation extends Metric{
    public Elevation(){
        //do nothing because object is purely a calculator
    }

    @Override
    public String display(GPX gpx){
        return "Total Elevation Gain: " + calculateMetricTotal(gpx).intValue() + " m";
    }
    @Override
    public ArrayList<Double> calculateDataPoints(GPX gpx) {
        ArrayList<Double> elevationPoints = new ArrayList<>();
        Track track = gpx.getTracks().get(0);
        TrackSegment segment = track.getSegments().get(0);
        List<WayPoint> waypoints = segment.getPoints();
        WayPoint previousPoint = waypoints.get(0);
        double elevGain;
        for(WayPoint point : waypoints){
            elevGain = point.getElevation().get().to(Length.Unit.METER) - previousPoint.getElevation().get().to(Length.Unit.METER);
            elevationPoints.add(elevGain);
            previousPoint = point;
        }
        return elevationPoints;
    }
    @Override
    public Double calculateMetricTotal(GPX gpx) {
        double totalElevation = 0.0;
        for(double point : calculateDataPoints(gpx)){
            if(point > 0)
                totalElevation += point;
        }
        return totalElevation;
    }
    @Override
    public Chart chartMetric(GPX gpx) {
        return new Chart();
    }
}
