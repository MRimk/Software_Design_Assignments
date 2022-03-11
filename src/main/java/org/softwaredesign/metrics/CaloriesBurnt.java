package org.softwaredesign.metrics;

import io.jenetics.jpx.GPX;
import org.softwaredesign.Chart;
import org.softwaredesign.GUI;

import java.util.ArrayList;

public class CaloriesBurnt extends Metric{
    public CaloriesBurnt(){
        //do nothing because object is purely a calculator
    }

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
        double met = 8;                       //TODO: we could do something similar to this table https://metscalculator.com/
        double userWeight = GUI.user.getWeight();
        return totalTimeInMinutes * met * 3.5 * userWeight / 200;
    }

    @Override
    public Chart chartMetric(GPX gpx) {
        return new Chart();
    }
}
