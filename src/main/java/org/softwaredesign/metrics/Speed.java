package org.softwaredesign.metrics;

import io.jenetics.jpx.GPX;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Speed extends Metric{
    static Metric speedInstance = null;

    private Speed(){
        //do nothing because object is purely a calculator
    }

    public static Metric getInstance() {
        if(speedInstance == null)
            speedInstance = new Speed();
        return speedInstance;
    }

    @Override
    public String display(GPX gpx){
        return "Average Speed: " + BigDecimal.valueOf(calculateMetricTotal(gpx)).setScale(2, RoundingMode.HALF_DOWN) + " " + getMetricUnits();
    }
    @Override
    public ArrayList<Double> calculateDataPoints(GPX gpx) {
        Metric timeCalculator = Time.getInstance();
        Metric distanceCalculator = Distance.getInstance();
        ArrayList<Double> timePoints = timeToIntervals(timeCalculator.calculateDataPoints(gpx));
        List<Double> distancePoints = distanceCalculator.calculateDataPoints(gpx);
        ArrayList<Double> speedPoints = new ArrayList<>();
        for(int i = 1; i < distancePoints.size(); i++){
            speedPoints.add(distancePoints.get(i)/timePoints.get(i));
        }
        return speedPoints;
    }

    private ArrayList<Double> timeToIntervals(List<Double> time) {
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
        Metric timeCalculator = Time.getInstance();
        Metric distanceCalculator = Distance.getInstance();
        return distanceCalculator.calculateMetricTotal(gpx)/timeCalculator.calculateMetricTotal(gpx);
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
        return "Speed";
    }
    @Override
    public String getMetricUnits(){
        return "km/h";
    }
}
