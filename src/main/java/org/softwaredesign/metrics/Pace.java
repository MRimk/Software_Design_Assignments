package org.softwaredesign.metrics;

import io.jenetics.jpx.GPX;
import java.util.ArrayList;

public class Pace extends Metric{
    public Pace(){
        //do nothing because object is purely a calculator
    }

    @Override
    public String display(GPX gpx){
        Double totalPace = calculateMetricTotal(gpx);
        int minutes = totalPace.intValue();
        double allSeconds = (totalPace - minutes) * 60.0;
        int seconds = (int)allSeconds;
        String returnText = "Average Pace: " + minutes + ":";
        String secondsString = (seconds < 10) ? "0" + seconds : "" + seconds;
        return returnText + secondsString + " min/km";
    }

    @Override
    public ArrayList<Double> calculateDataPoints(GPX gpx) {
        Speed speedCalculator = new Speed();
        ArrayList<Double> speedPoints = speedCalculator.calculateDataPoints(gpx);
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
        Time timeCalculator = new Time();
        Distance distanceCalculator = new Distance();
        return timeToMinutes(timeCalculator.calculateMetricTotal(gpx))/distanceCalculator.calculateMetricTotal(gpx);
    }
}
