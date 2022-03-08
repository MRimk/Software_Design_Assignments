package org.softwaredesign.metrics;

import io.jenetics.jpx.GPX;
import org.softwaredesign.Chart;
import org.softwaredesign.GUI;

import java.util.ArrayList;

public class CaloriesBurnt extends Metric{
    public CaloriesBurnt(){
        //do nothing because object is purely a calculator
    }
    public boolean needsUserData = true;

    @Override
    public String display(GPX gpx){
        return "Calories Burnt: " +  calculateMetricTotal(gpx).intValue() + " Cal";
    }
    @Override
    public ArrayList<Double> calculateDataPoints(GPX gpx) {
        ArrayList<Double> caloriesPoints = new ArrayList<>();
        caloriesPoints.add(calculateMetricTotal(gpx));
        return caloriesPoints;
    }
    @Override
    public Double calculateMetricTotal(GPX gpx) {
        Time timeCalculator = new Time();
        double totalTimeInMinutes = timeCalculator.calculateMetricTotal(gpx) * 60;
        double MET = 6.5;               //TODO: we could do something similar to this table https://metscalculator.com/
        double user_weight = 90.0;             //TODO: figure out how to pass user data
        double caloriesBurnt = totalTimeInMinutes * MET * 3.5 * user_weight / 200;
        return caloriesBurnt;
    }

    @Override
    public Chart chartMetric(GPX gpx) {
        return new Chart();
    }
}
