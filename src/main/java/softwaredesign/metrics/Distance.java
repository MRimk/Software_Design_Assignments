package softwaredesign.metrics;

import io.jenetics.jpx.*;
import org.apache.commons.math3.util.Precision;
import softwaredesign.Chart;



import java.util.ArrayList;
import java.util.List;

public class Distance extends Metric{
    public Distance(){
        //do nothing because object is purely a calculator
    }

    @Override
    public String display(GPX gpx){
        return "Total Distance: " + Precision.round(calculateMetricTotal(gpx), 2) + " km";
    }
    @Override
    public ArrayList<Double> calculateDataPoints(GPX gpx) {
        ArrayList<Double> distancePoints = new ArrayList<>();
        Track track = gpx.getTracks().get(0);
        TrackSegment segment = track.getSegments().get(0);
        List<WayPoint> waypoints = segment.getPoints();
        WayPoint previousPoint = waypoints.get(0);
        for(WayPoint point :waypoints){
            distancePoints.add(previousPoint.distance(point).to(Length.Unit.KILOMETER));
            previousPoint = point;
        }
        return distancePoints;
    }
    @Override
    public Double calculateMetricTotal(GPX gpx) {
        double totalDistance = 0.0;
        for(double point : calculateDataPoints(gpx)){
            totalDistance += point;
        }
        return totalDistance;
    }
    @Override
    public Chart chartMetric(GPX gpx) {
        return new Chart();
    }
}
