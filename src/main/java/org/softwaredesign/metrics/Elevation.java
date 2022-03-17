package org.softwaredesign.metrics;

import io.jenetics.jpx.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        List<WayPoint> waypoints = getWaypoints(gpx);
        double elevGain;
        Optional<Length> optionalElevation;
        Optional<Length> previousOptionalElevation = waypoints.get(0).getElevation();
        for(WayPoint point : waypoints){
            optionalElevation = point.getElevation();
            if(optionalElevation.isPresent() && previousOptionalElevation.isPresent()) {
                elevGain = optionalElevation.get().to(Length.Unit.METER) - previousOptionalElevation.get().to(Length.Unit.METER);
                elevationPoints.add(elevGain);
                previousOptionalElevation = optionalElevation;
            }
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
    public boolean isChartable(){
        return true;
    }
    @Override
    public boolean isUsedInGoals(){
        return true;
    }
    @Override
    public String getMetricName(){
        return "Elevation Gain";
    }
}
