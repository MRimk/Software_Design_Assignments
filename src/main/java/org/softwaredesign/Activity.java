package org.softwaredesign;

import io.jenetics.jpx.GPX;
import io.jenetics.jpx.WayPoint;
import org.softwaredesign.Helpers.SportToMetricsHelper;
import org.softwaredesign.metrics.Metric;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Activity {
    private final GPX gpx;
    private final SportTypes sport;

    public Activity(String gpxPath, SportTypes sport)
            throws IOException {
        this.sport = sport;
        gpx = GPX.read(gpxPath);
    }

    public String displayMetrics(){
        Metric[] metricsList = SportToMetricsHelper.getSportMetrics(sport);
        StringBuilder metricsText = new StringBuilder();
        metricsText.insert(0, "");
        for(Metric metric : metricsList){
            System.out.println(metric.display(gpx));
            metricsText.append(metric.display(gpx));
            metricsText.append("\n");
        }
        return metricsText.toString();
    }

    public List<List<Double>> getCoordinates(){
        List<WayPoint> waypoints = SportToMetricsHelper.getSportMetrics(sport)[0].getWaypoints(gpx);
        List<List<Double>> coords = new ArrayList<List<Double>>();
        List<Double> coordinate = new ArrayList<>();
        for(WayPoint point : waypoints){
            coordinate.add(point.getLatitude().doubleValue());
            coordinate.add(point.getLongitude().doubleValue());
            coords.add(coordinate);
        }
        return coords;
    }
}
