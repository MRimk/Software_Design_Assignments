package org.softwaredesign;

import io.jenetics.jpx.GPX;
import io.jenetics.jpx.WayPoint;
import org.jxmapviewer.viewer.GeoPosition;
import org.softwaredesign.enumerators.Sport;
import org.softwaredesign.helpers.SportToMetricsHelper;
import org.softwaredesign.metrics.Metric;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Activity {
    private final GPX gpx;
    private final Sport sport;


    public Activity(String gpxPath, Sport sport)
            throws IOException {
        this.sport = sport;
        gpx = GPX.read(gpxPath);
    }


    /**
     * Shows all the relevant to the activity metric strings in one block of text
     * @return
     * String containing all metric totals
     */
    public String displayMetrics(){
        Metric[] metricsList = SportToMetricsHelper.getSportMetrics(sport);
        StringBuilder metricsText = new StringBuilder();
        metricsText.insert(0, "");
        for(Metric metric : metricsList){
            metricsText.append(metric.display(gpx));
            metricsText.append("\n");
            notifyObserver(metric);
        }
        return metricsText.toString();
    }


    /**
     * Function to get the coordinates list from the GPX object
     * @return
     * List of GeoPosition objects which are the pairs of latitude and longitude
     */
    public List<GeoPosition> getGeoPositions() {
        List<WayPoint> waypoints = SportToMetricsHelper.getSportMetrics(sport)[0].getWaypoints(gpx);
        List<GeoPosition> coords = new ArrayList<>();
        GeoPosition geoPosition;
        for(WayPoint point : waypoints){
            geoPosition = new GeoPosition(point.getLatitude().doubleValue(), point.getLongitude().doubleValue());
            coords.add(geoPosition);
        }
        return coords;
    }

    /**
     * Call an update of the goals by providing data and relevant parser
     * @param metric
     * Metric object which acts like a parser for the data provided
     */
    private void notifyObserver(Metric metric){
        GUI.getGoalObserver().update(this, metric);
    }

    public GPX getGpx(){return gpx;}

    public Sport getSport(){return sport;}
}
