package org.softwaredesign.metrics;

import io.jenetics.jpx.GPX;
import io.jenetics.jpx.WayPoint;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class Time extends Metric {
    private Time(){
        //do nothing because object is purely a calculator
    }


    public static Metric getInstance() {
        if(instance == null || !instance.equals(new Time()))
            instance = new Time();
        return instance;
    }

    @Override
    public String display(GPX gpx){
        String time = "Elapsed Time: ";
        Double elapsedTime = calculateMetricTotal(gpx);
        int hours = elapsedTime.intValue();
        double minutesInDecimal = (elapsedTime - hours) * 60;
        int minutes = (int)minutesInDecimal;
        String minutesString = (minutes < 10) ? "0" + minutes : "" + minutes;
        int seconds = (int)((minutesInDecimal - minutes) * 60);
        String secondsString = (seconds < 10) ? "0" + seconds : "" + seconds;
        time += hours + ":" + minutesString + ":" +  secondsString;
        return time;
    }
    @Override
    public ArrayList<Double> calculateDataPoints(GPX gpx) {
        ArrayList<Double> timePoints = new ArrayList<>();
        List<WayPoint> waypoints = getWaypoints(gpx);
        Optional<ZonedDateTime> time;
        ZonedDateTime timePoint;
        for(WayPoint point : waypoints){
            time = point.getTime();
            if(time.isPresent()) {
                timePoint = time.get();
                timePoints.add(timePoint.getHour() + timePoint.getMinute() / 60.0 + timePoint.getSecond() / 3600.0);
            }
        }
        return timePoints;
    }
    @Override
    public Double calculateMetricTotal(GPX gpx) {
        ArrayList<Double> timePoints = calculateDataPoints(gpx);
        return timePoints.get(timePoints.size() - 1) - timePoints.get(0);
    }
    @Override
    public boolean isChartable(){
        return false;
    }
    @Override
    public boolean isUsedInGoals(){
        return true;
    }
    @Override
    public String getMetricName(){
        return "Time";
    }
    @Override
    public String getMetricUnits(){
        return "";
    }
}
