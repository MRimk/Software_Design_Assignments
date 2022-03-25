package org.softwaredesign.metrics;

import io.jenetics.jpx.GPX;
import io.jenetics.jpx.WayPoint;
import org.w3c.dom.DOMException;

import java.util.ArrayList;
import java.util.List;

public class Cadence extends Metric{
    private Cadence(){
        //do nothing because object is purely a calculator
    }
    public static Metric getInstance() {
        if(instance == null || !instance.equals(new Cadence()))
            instance = new Cadence();
        return instance;
    }

    @Override
    public String display(GPX gpx){
        int value = calculateMetricTotal(gpx).intValue();
        if(value == -1.0) return "Cadence N/A";
        return "Average Cadence: " +  value + " " + getMetricUnits();
    }
    @Override
    public ArrayList<Double> calculateDataPoints(GPX gpx) {
        ArrayList<Double> cadencePoints = new ArrayList<>();
        List<WayPoint> waypoints = getWaypoints(gpx);
        try {
            for (WayPoint point : waypoints) {
                point.getExtensions().ifPresent(extensions ->
                        // adjustment made because of weird Garmin protocol
                        cadencePoints.add(Double.parseDouble(extensions.getElementsByTagName("ns3:cad").item(0).getTextContent()) + 88)
                );
            }
        }
        catch (DOMException exception){
            System.err.println("Cadence not found in gpx file");
        }
        return cadencePoints;
    }
    @Override
    public Double calculateMetricTotal(GPX gpx) {
        ArrayList<Double> allCadencePoints = calculateDataPoints(gpx);
        if(allCadencePoints.isEmpty()) return -1.0;
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