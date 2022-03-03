package softwaredesign.metrics;

import org.alternativevision.gpx.beans.GPX;
import org.alternativevision.gpx.beans.Waypoint;
import org.apache.commons.math3.util.Precision;
import softwaredesign.Chart;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class Distance implements Metric{
    private Distance(){}

    static String display(GPX gpx){
        return "" + Precision.round(calculateMetricTotal(gpx), 2) + " km";
    }
    static ArrayList<Double> calculateDataPoints(GPX gpx) {
        HashSet<Waypoint> waypoints = gpx.getWaypoints();
        Iterator<Waypoint> iter = waypoints.iterator();
        ArrayList<Double> distancePoints = new ArrayList<>();

        double distanceBetweenPoints;
        Waypoint lastElement = iter.next();
        Waypoint currentElement;
        while(iter.hasNext()){
            currentElement = iter.next();
            distanceBetweenPoints = currentElement.distanceTo(lastElement);
            distancePoints.add(distanceBetweenPoints);
            lastElement = currentElement;
        }
        return distancePoints;
    }
    static Double calculateMetricTotal(GPX gpx) {
        double totalDistance = 0.0;
        for(double point : calculateDataPoints(gpx)){
            totalDistance += point;
        }
        return totalDistance;
    }
    static Chart chartMetric(GPX gpx) {
        return new Chart();
    }
}
