package org.softwaredesign.metrics;

import io.jenetics.jpx.GPX;
import java.util.ArrayList;
import java.util.List;

public class Pace extends Metric{
    private Pace(){
        //do nothing because object is purely a calculator
    }
    public static Metric getInstance() {
        if(instance == null || !instance.equals(new Pace()))
            instance = new Pace();
        return instance;
    }

    @Override
    public String display(GPX gpx){
        Double totalPace = calculateMetricTotal(gpx);
        int minutes = totalPace.intValue();
        double allSeconds = (totalPace - minutes) * 60.0;
        int seconds = (int)allSeconds;
        String returnText = "Average Pace: " + minutes + ":";
        String secondsString = (seconds < 10) ? "0" + seconds : "" + seconds;
        return returnText + secondsString + " " + getMetricUnits();
    }

    @Override
    public ArrayList<Double> calculateDataPoints(GPX gpx) {
        Metric speedCalculator = Speed.getInstance();
        List<Double> speedPoints = speedCalculator.calculateDataPoints(gpx);
        ArrayList<Double> pacePoints = new ArrayList<>();
        for(Double point : speedPoints){
            pacePoints.add(1 / point);
        }
        return pacePoints;
    }

    private Double timeToMinutes(Double timePoint) {
        return timePoint * 60.0;
    }

    @Override
    public Double calculateMetricTotal(GPX gpx) {
        Metric timeCalculator = Time.getInstance();
        Metric distanceCalculator = Distance.getInstance();
        return timeToMinutes(timeCalculator.calculateMetricTotal(gpx))/distanceCalculator.calculateMetricTotal(gpx);
    }

    @Override
    public boolean isChartable(){
        return false;
    }
    @Override
    public boolean isUsedInGoals(){
        return false;
    }
    @Override
    public String getMetricName(){
        return "Pace";
    }
    @Override
    public String getMetricUnits(){
        return "min/km";
    }
}
