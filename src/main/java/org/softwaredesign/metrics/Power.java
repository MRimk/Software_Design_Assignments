package org.softwaredesign.metrics;

import io.jenetics.jpx.GPX;
import org.softwaredesign.GUI;

import java.util.ArrayList;
import java.util.List;

public class Power extends Metric{
    static Metric powerInstance = null;

    private Power(){
        //do nothing because object is purely a calculator
    }
    public static Metric getInstance() {
        if(powerInstance == null)
            powerInstance = new Power();
        return powerInstance;
    }

    private static final double DRIVETRAIN_LOSS = 0.09;
    private static final double GRAVITATIONAL_FORCE_CONSTANT = 9.8067;
    private static final double GRADE = 1.0;
    private static final double BICYCLE_WEIGHT = 15.0;
    private static final double ROLLING_RESISTANCE_COEFFICIENT = 0.0219;
    private static final double DRAG_COEFFICIENT = 0.4; // taken from https://bicycles.stackexchange.com/questions/44092/what-is-the-average-cda-to-weight-relationship-of-a-professional-road-cyclist


    @Override
    public String display(GPX gpx){
        return "Average Power: " +  calculateMetricTotal(gpx).intValue() + " " + getMetricUnits();
    }
    @Override
    public ArrayList<Double> calculateDataPoints(GPX gpx) {
        ArrayList<Double> powerPoints = new ArrayList<>();
        Metric speedCalculator = Speed.getInstance();
        List<Double> speedPoints = speedCalculator.calculateDataPoints(gpx);

        double powerPoint;
        double forceGravity = fGravity();
        double forceRolling = fRolling();
        for(Double velocityPoint : speedPoints){
            powerPoint = (1-DRIVETRAIN_LOSS) * (forceGravity + forceRolling + fDrag(velocityPoint)) * velocityPoint;
            powerPoints.add(powerPoint);
        }
        return powerPoints;
    }

    //calculations are followed from https://www.gribble.org/cycling/power_v_speed.html
    private double fDrag(Double velocityPoint) {
        return 0.5 * velocityPoint * velocityPoint * DRAG_COEFFICIENT;
    }

    private double fRolling() {
        return GRAVITATIONAL_FORCE_CONSTANT * Math.cos(Math.atan(GRADE/100)) * (BICYCLE_WEIGHT + GUI.getUser().getWeight()) * ROLLING_RESISTANCE_COEFFICIENT;
    }

    private double fGravity() {
        return GRAVITATIONAL_FORCE_CONSTANT * Math.sin(Math.atan(GRADE/100)) * (BICYCLE_WEIGHT + GUI.getUser().getWeight());
    }

    @Override
    public Double calculateMetricTotal(GPX gpx) {
        Metric timeCalculator = Time.getInstance();
        double velocityPoint = timeCalculator.calculateMetricTotal(gpx);
        return  (1-DRIVETRAIN_LOSS) * (fGravity() + fRolling() + fDrag(velocityPoint)) * velocityPoint;
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
        return "Power";
    }
    @Override
    public String getMetricUnits(){
        return "W";
    }
}
