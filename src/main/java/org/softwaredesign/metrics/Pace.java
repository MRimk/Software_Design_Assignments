package org.softwaredesign.metrics;

import io.jenetics.jpx.GPX;
import org.softwaredesign.Chart;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Pace extends Metric{
    public Pace(){
        //do nothing because object is purely a calculator
    }

    @Override
    public String display(GPX gpx){
        String averagePace = "Average Pace: ";
        Double totalPace = calculateMetricTotal(gpx);
        int minutes = totalPace.intValue();
        double allSeconds = (totalPace - minutes) * 60.0;
        int seconds = (int)allSeconds;
        return "Average Pace: " + minutes + ":" + seconds + " min/km";
    }
    @Override
    public ArrayList<Double> calculateDataPoints(GPX gpx) {
        Time timeCalculator = new Time();
        Distance distanceCalculator = new Distance();
        ArrayList<Double> timePoints = timeToGaps(timeCalculator.calculateDataPoints(gpx));
        ArrayList<Double> distancePoints = distanceCalculator.calculateDataPoints(gpx);
        ArrayList<Double> speedPoints = new ArrayList<>();
        for(int i = 1; i < distancePoints.size(); i++){
            speedPoints.add(timePoints.get(i)/distancePoints.get(i));
        }
        return speedPoints;
    }

    private Double timeToMinutes(Double timePoint) {
        return timePoint * 60.0;
    }

    private ArrayList<Double> timeToGaps(ArrayList<Double> time) {
        ArrayList<Double> timeGaps = new ArrayList<>();
        Double previousTime = time.get(0);
        for(Double timePoint : time){
            timeGaps.add(timeToMinutes(timePoint - previousTime));
            previousTime = timePoint;
        }
        return timeGaps;
    }

    @Override
    public Double calculateMetricTotal(GPX gpx) {
        Time timeCalculator = new Time();
        Distance distanceCalculator = new Distance();
        return timeToMinutes(timeCalculator.calculateMetricTotal(gpx))/distanceCalculator.calculateMetricTotal(gpx);
    }
    @Override
    public Chart chartMetric(GPX gpx) {
        return new Chart();
    }
}
