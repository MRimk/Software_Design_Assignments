package softwaredesign.metrics;

import io.jenetics.jpx.*;
import org.apache.commons.math3.util.Precision;
import softwaredesign.Chart;

import java.util.ArrayList;

public class Speed extends Metric{
    public Speed(){
        //do nothing because object is purely a calculator
    }

    @Override
    public String display(GPX gpx){
        return "Average Speed: " + Precision.round(calculateMetricTotal(gpx), 2) + " km/h";
    }
    @Override
    public ArrayList<Double> calculateDataPoints(GPX gpx) {
        Time timeCalculator = new Time();
        Distance distanceCalculator = new Distance();
        ArrayList<Double> timePoints = timeToGaps(timeCalculator.calculateDataPoints(gpx));

        ArrayList<Double> distancePoints = distanceCalculator.calculateDataPoints(gpx);
        ArrayList<Double> speedPoints = new ArrayList<>();
        for(int i = 1; i < distancePoints.size(); i++){
            speedPoints.add(distancePoints.get(i)/timePoints.get(i));
        }
        return speedPoints;
    }

    private ArrayList<Double> timeToGaps(ArrayList<Double> time) {
        ArrayList<Double> timeGaps = new ArrayList<>();
        Double previousTime = time.get(0);
        for(Double timePoint : time){
            timeGaps.add(timePoint - previousTime);
            previousTime = timePoint;
        }
        return timeGaps;
    }

    @Override
    public Double calculateMetricTotal(GPX gpx) {
        Time timeCalculator = new Time();
        Distance distanceCalculator = new Distance();
        return distanceCalculator.calculateMetricTotal(gpx)/timeCalculator.calculateMetricTotal(gpx);
    }
    @Override
    public Chart chartMetric(GPX gpx) {
        return new Chart();
    }
}
