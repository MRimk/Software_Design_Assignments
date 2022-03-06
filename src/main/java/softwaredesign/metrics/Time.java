package softwaredesign.metrics;

import org.apache.commons.math3.util.Precision;
import softwaredesign.Chart;
import io.jenetics.jpx.*;


import java.time.ZonedDateTime;
import java.util.*;


public class Time extends Metric {
    public Time(){
        //do nothing because object is purely a calculator
    }
    @Override
    public String display(GPX gpx){
        String time = "Elapsed Time: ";
        Double elapsedTime = calculateMetricTotal(gpx);
        int hours = elapsedTime.intValue();
        Double minutesInDecimal = elapsedTime.doubleValue() * 60;
        double seconds = minutesInDecimal.doubleValue() * 60;
        int minutes = minutesInDecimal.intValue();
        time += hours + ":" + minutes + ":" + Precision.round(seconds, 2);

        return time;
    }
    @Override
    public ArrayList<Double> calculateDataPoints(GPX gpx) {
        ArrayList<Double> timePoints = new ArrayList<>();
        Track track = gpx.getTracks().get(0);
        TrackSegment segment = track.getSegments().get(0);
        List<WayPoint> waypoints = segment.getPoints();
        Optional<ZonedDateTime> time = waypoints.get(0).getTime();
        for(WayPoint point : waypoints){
            time = point.getTime();
            timePoints.add(time.get().getHour() + time.get().getMinute() / 60.0 + time.get().getSecond() / 3600.0);
        }
        return timePoints;
    }
    @Override
    public Double calculateMetricTotal(GPX gpx) {
        ArrayList<Double> timePoints = calculateDataPoints(gpx);
        return timePoints.get(timePoints.size() - 1) - timePoints.get(0);
    }

    private Double convertMillisecondsToHours(Long elapsedTime) {
        return elapsedTime / 1000.0 / 60.0 / 60.0 ;
    }

    @Override
    public Chart chartMetric(GPX gpx) {
        return new Chart();
    }
}
