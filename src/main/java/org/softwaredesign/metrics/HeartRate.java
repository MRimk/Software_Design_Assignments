package org.softwaredesign.metrics;

import io.jenetics.jpx.*;
import org.softwaredesign.Chart;
import org.w3c.dom.Document;

import java.lang.annotation.Documented;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HeartRate extends Metric{
    public HeartRate(){
        //do nothing because object is purely a calculator
    }

    @Override
    public String display(GPX gpx){
        return "Average Heart Rate: " +  calculateMetricTotal(gpx).intValue() + " bpm";
    }
    @Override
    public ArrayList<Double> calculateDataPoints(GPX gpx) {
        ArrayList<Double> hrPoints = new ArrayList<>();
        List<WayPoint> waypoints = getWaypoints(gpx);
        for(WayPoint point :waypoints){
            point.getExtensions().ifPresent( extensions -> {
                        if (extensions.getElementsByTagName("ns3:hr").getLength() != 0) {
                            hrPoints.add(Double.parseDouble(extensions.getElementsByTagName("ns3:hr").item(0).getTextContent()));
                        }
                    }
            );
        }
        return hrPoints;
    }
    @Override
    public Double calculateMetricTotal(GPX gpx) {
        ArrayList<Double> allHRPoints = calculateDataPoints(gpx);

        double sumOfPoints = 0.0;
        for(double point : allHRPoints){
            sumOfPoints += point;
        }
        return sumOfPoints / allHRPoints.size();
    }
}
