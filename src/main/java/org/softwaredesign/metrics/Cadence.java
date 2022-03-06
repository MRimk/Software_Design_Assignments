package org.softwaredesign.metrics;

import io.jenetics.jpx.GPX;
import io.jenetics.jpx.Track;
import io.jenetics.jpx.TrackSegment;
import io.jenetics.jpx.WayPoint;
import org.softwaredesign.Chart;

import java.util.ArrayList;
import java.util.List;

public class Cadence extends Metric{
    public Cadence(){
        //do nothing because object is purely a calculator
    }

    @Override
    public String display(GPX gpx){
        return "Average Cadence: " +  calculateMetricTotal(gpx).intValue() + " spm";
    }
    @Override
    public ArrayList<Double> calculateDataPoints(GPX gpx) {
        ArrayList<Double> cadencePoints = new ArrayList<>();
        Track track = gpx.getTracks().get(0);
        TrackSegment segment = track.getSegments().get(0);
        List<WayPoint> waypoints = segment.getPoints();

        for(WayPoint point :waypoints){
            point.getExtensions().ifPresent( extensions ->
                    cadencePoints.add(Double.parseDouble(extensions.getElementsByTagName("ns3:cad").item(0).getTextContent()))
            );
        }
        return cadencePoints;
    }
    @Override
    public Double calculateMetricTotal(GPX gpx) {
        ArrayList<Double> allCadencePoints = calculateDataPoints(gpx);

        double sumOfPoints = 0.0;
        for(double point : allCadencePoints){
            sumOfPoints += point;
        }
        return sumOfPoints / allCadencePoints.size();
    }

    @Override
    public Chart chartMetric(GPX gpx) {
        return new Chart();
    }
}