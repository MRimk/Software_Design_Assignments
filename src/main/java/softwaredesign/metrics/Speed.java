package softwaredesign.metrics;

import org.alternativevision.gpx.beans.GPX;
import org.apache.commons.math3.util.Precision;
import softwaredesign.Chart;

import java.util.ArrayList;

public class Speed implements Metric{
    private Speed(){}

    static String display(GPX gpx){
        return "" + Precision.round(calculateMetricTotal(gpx), 2) + " km/h";
    }
    static ArrayList<Double> calculateDataPoints(GPX gpx) {
        ArrayList<Double> timePoints = Time.calculateDataPoints(gpx);
        ArrayList<Double> distancePoints = Distance.calculateDataPoints(gpx);
        ArrayList<Double> speedPoints = new ArrayList<>();
        for(int i = 0; i < distancePoints.size(); i++){
            speedPoints.add(distancePoints.get(i)/timePoints.get(i));
        }
        return speedPoints;
    }
    static Double calculateMetricTotal(GPX gpx) {
        return Distance.calculateMetricTotal(gpx)/Time.calculateMetricTotal(gpx);
    }
    static Chart chartMetric(GPX gpx) {
        return new Chart();
    }
}
