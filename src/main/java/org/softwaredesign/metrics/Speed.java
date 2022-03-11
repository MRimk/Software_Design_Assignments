package org.softwaredesign.metrics;

import io.jenetics.jpx.GPX;
import org.softwaredesign.Chart;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Speed extends Metric{
    public Speed(){
        //do nothing because object is purely a calculator
    }

    @Override
    public String display(GPX gpx){
        return "Average Speed: " + BigDecimal.valueOf(calculateMetricTotal(gpx)).setScale(2, RoundingMode.HALF_DOWN) + " km/h";
    }
    @Override
    public ArrayList<Double> calculateDataPoints(GPX gpx) {
        Time timeCalculator = new Time();
        Distance distanceCalculator = new Distance();
        ArrayList<Double> timePoints = timeToIntervals(timeCalculator.calculateDataPoints(gpx));

        ArrayList<Double> distancePoints = distanceCalculator.calculateDataPoints(gpx);
        ArrayList<Double> speedPoints = new ArrayList<>();
        for(int i = 1; i < distancePoints.size(); i++){
            speedPoints.add(distancePoints.get(i)/timePoints.get(i));
        }
        return speedPoints;
    }

    private ArrayList<Double> timeToIntervals(ArrayList<Double> time) {
        ArrayList<Double> timeGaps = new ArrayList<>();
        Double previousTime = time.get(0);
        for(Double timePoint : time){
            timeGaps.add(timePoint - previousTime);
            previousTime = timePoint;
        }
        return timeGaps;
    }

    @Override
    public Double calculateMetricTotal(GPX gpx) {
        Time timeCalculator = new Time();
        Distance distanceCalculator = new Distance();
        return distanceCalculator.calculateMetricTotal(gpx)/timeCalculator.calculateMetricTotal(gpx);
    }
}
