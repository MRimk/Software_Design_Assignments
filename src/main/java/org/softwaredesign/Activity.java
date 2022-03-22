package org.softwaredesign;

import io.jenetics.jpx.GPX;
import io.jenetics.jpx.WayPoint;
import org.softwaredesign.enumerators.Sport;
import org.softwaredesign.helpers.SportToMetricsHelper;
import org.softwaredesign.helpers.StringToSportHelper;
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

    public String displayMetrics(){
        Metric[] metricsList = SportToMetricsHelper.getSportMetrics(sport);
        StringBuilder metricsText = new StringBuilder();
        metricsText.insert(0, "");
        for(Metric metric : metricsList){
            metricsText.append(metric.display(gpx));
            metricsText.append("\n");
            updateGoals(metric);
        }
        return metricsText.toString();
    }

    private void updateGoals(Metric metric) {
        Integer numGoals = GUI.getUser().getNumGoals();
        List<Goal> goalList = GUI.getUser().getGoalList();

        for (int i = 0; i < numGoals; i++) {
            Goal currentGoal = goalList.get(i);
            Sport goalSport = StringToSportHelper.getSport(currentGoal.getSport());
            if (goalSport.equals(sport)) {
                if (currentGoal.getMetric().equals(metric.getMetricName())) {
                    GUI.getUser().updateGoal(metric.calculateMetricTotal(gpx), i);
                }
            }
        }
        GUI.getUser().saveUserData();
    }

    public List<List<Double>> getCoordinates(){
        List<WayPoint> waypoints = SportToMetricsHelper.getSportMetrics(sport)[0].getWaypoints(gpx);
        List<List<Double>> coords = new ArrayList<>();
        List<Double> coordinate = new ArrayList<>();
        for(WayPoint point : waypoints){
            coordinate.add(point.getLatitude().doubleValue());
            coordinate.add(point.getLongitude().doubleValue());
            coords.add(coordinate);
        }
        return coords;
    }

    public GPX getGpx(){return gpx;}

    public Sport getSport(){return sport;}
}
