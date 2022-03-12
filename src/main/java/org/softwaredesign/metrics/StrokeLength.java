package org.softwaredesign.metrics;

import io.jenetics.jpx.GPX;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class StrokeLength extends Metric {
    public StrokeLength(){
        //do nothing because object is purely a calculator
    }

    @Override
    public String display(GPX gpx){
        return "Average Stroke Length: " +  BigDecimal.valueOf(calculateMetricTotal(gpx)).setScale(2, RoundingMode.HALF_DOWN) + " m";
    }
    @Override
    public ArrayList<Double> calculateDataPoints(GPX gpx) {
        ArrayList<Double> strokePoints = new ArrayList<>();
        Cadence cadenceCalculator = new Cadence();
        ArrayList<Double> cadencePoints = cadenceCalculator.calculateDataPoints(gpx);
        cadencePoints = cadencePoints.stream().filter(item -> item > 0).collect(Collectors.toCollection(ArrayList::new));
        Distance distanceCalculator = new Distance();
        double totalDistance = distanceCalculator.calculateMetricTotal(gpx) * 1000; //convert to meters
        Time timeCalculator = new Time();
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
        return sumOfPoints / strokeLengthPoints.size();
    }
}