package org.softwaredesign.metrics;

import io.jenetics.jpx.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Distance extends Metric{
    private Distance(){
        //do nothing because object is purely a calculator
    }
    public static Metric getInstance() {
        if(instance == null || !instance.equals(new Distance()))
            instance = new Distance();
        return instance;
    }

    @Override
    public String display(GPX gpx){
        return "Total Distance: " + BigDecimal.valueOf(calculateMetricTotal(gpx)).setScale(2, RoundingMode.HALF_DOWN) + " " + getMetricUnits();
    }
    @Override
    public ArrayList<Double> calculateDataPoints(GPX gpx) {
        ArrayList<Double> distancePoints = new ArrayList<>();
        List<WayPoint> waypoints = getWaypoints(gpx);
        WayPoint previousPoint = waypoints.get(0);
        for(WayPoint point :waypoints){
            distancePoints.add(previousPoint.distance(point).to(Length.Unit.KILOMETER));
            previousPoint = point;
        }
        return distancePoints;
    }
    @Override
    public Double calculateMetricTotal(GPX gpx) {
        double totalDistance = 0.0;
        for(double point : calculateDataPoints(gpx)){
            totalDistance += point;
        }
        return totalDistance;
    }

    @Override
    public boolean isChartable(){
        return false;
    }
    @Override
    public boolean isUsedInGoals(){
        return true;
    }
    @Override
    public String getMetricName(){
        return "Distance";
    }
    @Override
    public String getMetricUnits(){
        return "km";
    }
}
