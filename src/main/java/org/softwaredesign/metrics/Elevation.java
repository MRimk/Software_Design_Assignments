package org.softwaredesign.metrics;

import io.jenetics.jpx.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Elevation extends Metric{
    static Metric elevationInstance = null;

    private Elevation(){
        //do nothing because object is purely a calculator
    }
    public static Metric getInstance() {
        if(elevationInstance == null)
            elevationInstance = new Elevation();
        return elevationInstance;
    }

    @Override
    public String display(GPX gpx){
        double value = calculateMetricTotal(gpx).intValue();
        if(value == Integer.MAX_VALUE) return "Elevation N/A";
        return "Total Elevation Gain: " + value + " " + getMetricUnits();
    }
    @Override
    public ArrayList<Double> calculateDataPoints(GPX gpx) {
        ArrayList<Double> elevationPoints = new ArrayList<>();
        List<WayPoint> waypoints = getWaypoints(gpx);
        double elevGain;
        Optional<Length> optionalElevation;
        Optional<Length> previousOptionalElevation = waypoints.get(0).getElevation();
        try{
            for(WayPoint point : waypoints){
                optionalElevation = point.getElevation();
                if(optionalElevation.isPresent() && previousOptionalElevation.isPresent()) {
                    elevGain = optionalElevation.get().to(Length.Unit.METER) - previousOptionalElevation.get().to(Length.Unit.METER);
                    elevationPoints.add(elevGain);
                    previousOptionalElevation = optionalElevation;
                }
                else {
                    elevationPoints = new ArrayList<>();
                    throw new NoSuchFieldException();
                }
            }
        }
        catch (NoSuchFieldException exception){
            System.err.println("Elevation data corrupted");
        }
        return elevationPoints;
    }
    @Override
    public Double calculateMetricTotal(GPX gpx) {
        double totalElevation = 0.0;
        List<Double> allPoints = calculateDataPoints(gpx);
        if(allPoints.isEmpty()) return (double)Integer.MAX_VALUE;
        for(double point : allPoints){
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
    @Override
    public String getMetricUnits(){
        return "m";
    }
}
