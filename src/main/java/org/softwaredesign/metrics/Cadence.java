package org.softwaredesign.metrics;

import io.jenetics.jpx.GPX;
import io.jenetics.jpx.WayPoint;
import java.util.ArrayList;
import java.util.List;

public class Cadence extends Metric{    //TODO: make class singleton
    public Cadence(){
        //do nothing because object is purely a calculator
    }

    @Override
    public String display(GPX gpx){
        return "Average Cadence: " +  calculateMetricTotal(gpx).intValue() + " " + getMetricUnits();
    }
    @Override
    public ArrayList<Double> calculateDataPoints(GPX gpx) {
        ArrayList<Double> cadencePoints = new ArrayList<>();
        List<WayPoint> waypoints = getWaypoints(gpx);
        for(WayPoint point :waypoints){
            point.getExtensions().ifPresent( extensions ->
                    // adjustment made because of weird Garmin protocol
                    cadencePoints.add(Double.parseDouble(extensions.getElementsByTagName("ns3:cad").item(0).getTextContent()) + 88)
            );
        }
        return cadencePoints;
    }
    @Override
    public Double calculateMetricTotal(GPX gpx) {
        ArrayList<Double> allCadencePoints = calculateDataPoints(gpx);

        double sumOfPoints = 0.0;
        int counter = 1;
        for(double point : allCadencePoints){
            if(point != 0) counter++;
            sumOfPoints += point;
        }
        return sumOfPoints / counter;
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
        return "Cadence";
    }
    @Override
    public String getMetricUnits(){
        return "spm";
    }
}