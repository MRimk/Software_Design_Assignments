package org.softwaredesign.metrics;

import io.jenetics.jpx.GPX;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StrokeLength extends Metric {
    static Metric strokeInstance = null;

    private StrokeLength(){
        //do nothing because object is purely a calculator
    }

    public static Metric getInstance() {
        if(strokeInstance == null)
            strokeInstance = new StrokeLength();
        return strokeInstance;
    }
    @Override
    public String display(GPX gpx){
        BigDecimal value = BigDecimal.valueOf(calculateMetricTotal(gpx)).setScale(2, RoundingMode.HALF_DOWN);
        if(value.doubleValue() == -1.0) return "Stroke Length N/A";
        return "Average Stroke Length: " + value + " " + getMetricUnits();
    }
    @Override
    public ArrayList<Double> calculateDataPoints(GPX gpx) {
        ArrayList<Double> strokePoints = new ArrayList<>();
        Metric cadenceCalculator = Cadence.getInstance();
        List<Double> cadencePoints = cadenceCalculator.calculateDataPoints(gpx);
        cadencePoints = cadencePoints.stream().filter(item -> item > 0).collect(Collectors.toCollection(ArrayList::new));
        Metric distanceCalculator = Distance.getInstance();
        double totalDistance = distanceCalculator.calculateMetricTotal(gpx) * 1000; //convert to meters
        Metric timeCalculator = Time.getInstance();
        double totalTimeInMinutes = timeCalculator.calculateMetricTotal(gpx) * 60;  //convert to meters
        for(double cadencePoint : cadencePoints){
            strokePoints.add(totalDistance / (cadencePoint * totalTimeInMinutes));
        }
        return strokePoints;
    }
    @Override
    public Double calculateMetricTotal(GPX gpx) {
        ArrayList<Double> strokeLengthPoints = calculateDataPoints(gpx);
        double sumOfPoints = 0.0;
        for(double point : strokeLengthPoints){
            sumOfPoints += point;
        }
        if(strokeLengthPoints.isEmpty()) return -1.0;
        return sumOfPoints / strokeLengthPoints.size();
    }
    @Override
    public boolean isChartable(){
        return true;
    }
    @Override
    public boolean isUsedInGoals(){
        return false;
    }
    @Override
    public String getMetricName(){
        return "Stroke Length";
    }
    @Override
    public String getMetricUnits(){
        return "m";
    }
}