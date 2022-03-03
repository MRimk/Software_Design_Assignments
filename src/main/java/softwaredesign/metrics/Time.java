package softwaredesign.metrics;

import org.alternativevision.gpx.beans.Waypoint;
import org.apache.commons.math3.util.Precision;
import softwaredesign.Chart;
import org.alternativevision.gpx.beans.GPX;

import java.util.*;


public class Time implements Metric {
    private Time(){}

    public static String display(GPX gpx){
        String time = "";
        Double elapsedTime = calculateMetricTotal(gpx);
        int hours = elapsedTime.intValue();
        Double minutesInDecimal = elapsedTime.doubleValue() * 60;
        double seconds = minutesInDecimal.doubleValue() * 60;
        int minutes = minutesInDecimal.intValue();
        time += hours + ":" + minutes + ":" + Precision.round(seconds, 2);

        return time;
    }

    static ArrayList<Double> calculateDataPoints(GPX gpx) {
        HashSet<Waypoint> waypoints =  gpx.getWaypoints();
        Iterator<Waypoint> iter = waypoints.iterator();
        ArrayList<Double> timePoints = new ArrayList<>();
        while(iter.hasNext()){
            timePoints.add(convertMillisecondsToHours(iter.next().getTime().getTime()));
        }
        return timePoints;
    }
    static Double calculateMetricTotal(GPX gpx) {
        ArrayList<Double> timePoints = calculateDataPoints(gpx);
        return timePoints.get(timePoints.size() - 1) - timePoints.get(0);
    }

    private static Double convertMillisecondsToHours(Long elapsedTime) {

        return elapsedTime / 1000.0 / 60.0 / 60.0 ;
    }

    static Chart chartMetric(GPX gpx) {
        return new Chart();
    }
}
