package org.softwaredesign.metrics;

import io.jenetics.jpx.GPX;
import org.softwaredesign.Chart;

import java.util.ArrayList;

public class Power extends Metric{
    public Power(){
        //do nothing because object is purely a calculator
    }

    private static final double DRIVETRAIN_LOSS = 0.09;
    private static final double GRAVITATIONAL_FORCE_CONSTANT  = 9.8067;
    private static final double GRADE = 1.0;
    private static final double BICYCLE_WEIGHT  = 14.0;
    private static final double ROLLING_RESISTANCE_COEFFICIENT  = 0.0219;
    private static final double DRAG_COEFFICIENT  = 0.4; // taken from https://bicycles.stackexchange.com/questions/44092/what-is-the-average-cda-to-weight-relationship-of-a-professional-road-cyclist


    @Override
    public String display(GPX gpx){
        return "Average Power: " +  calculateMetricTotal(gpx).intValue() + " W";
    }
    @Override
    public ArrayList<Double> calculateDataPoints(GPX gpx) {
        ArrayList<Double> powerPoints = new ArrayList<>();
        Speed speedCalculator = new Speed();
        ArrayList<Double> speedPoints = speedCalculator.calculateDataPoints(gpx);


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

        return GRAVITATIONAL_FORCE_CONSTANT * Math.cos(Math.atan(GRADE/100)) * BICYCLE_WEIGHT * ROLLING_RESISTANCE_COEFFICIENT;
    }

    private double fGravity() {
        return GRAVITATIONAL_FORCE_CONSTANT * Math.sin(Math.atan(GRADE/100)) * BICYCLE_WEIGHT;
    }

    @Override
    public Double calculateMetricTotal(GPX gpx) {
        Time timeCalculator = new Time();
        double velocityPoint = timeCalculator.calculateMetricTotal(gpx);
        return  (1-DRIVETRAIN_LOSS) * (fGravity() + fRolling() + fDrag(velocityPoint)) * velocityPoint;
    }
    @Override
    public Chart chartMetric(GPX gpx) {
        return new Chart();
    }
}
