package org.softwaredesign.metrics;

import io.jenetics.jpx.*;
import org.w3c.dom.DOMException;

import java.util.ArrayList;
import java.util.List;

public class HeartRate extends Metric{
    private HeartRate(){
        //do nothing because object is purely a calculator
    }

    public static Metric getInstance() {
        if(instance == null || !instance.equals(new HeartRate()))
            instance = new HeartRate();
        return instance;
    }

    @Override
    public String display(GPX gpx){
        int value = calculateMetricTotal(gpx).intValue();
        if(value == -1.0) return "Heart Rate N/A";
        return "Average Heart Rate: " +  value + " " + getMetricUnits();
    }
    @Override
    public ArrayList<Double> calculateDataPoints(GPX gpx) {
        ArrayList<Double> hrPoints = new ArrayList<>();
        List<WayPoint> waypoints = getWaypoints(gpx);
        try {
            for (WayPoint point : waypoints) {
                point.getExtensions().ifPresent(extensions -> {
                            if (extensions.getElementsByTagName("ns3:hr").getLength() != 0) {
                                hrPoints.add(Double.parseDouble(extensions.getElementsByTagName("ns3:hr").item(0).getTextContent()));
                            }
                        }
                );
            }
        }
        catch (DOMException exception){
            System.err.println("HR not found in gpx file");
        }
        return hrPoints;
    }
    @Override
    public Double calculateMetricTotal(GPX gpx) {
        ArrayList<Double> allHRPoints = calculateDataPoints(gpx);
        if(allHRPoints.isEmpty()) return -1.0;
        double sumOfPoints = 0.0;
        for(double point : allHRPoints){
            sumOfPoints += point;
        }
        return sumOfPoints / allHRPoints.size();
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
        return "Heart Rate";
    }
    @Override
    public String getMetricUnits(){
        return "bpm";
    }
}
