package org.softwaredesign.metrics;


import io.jenetics.jpx.GPX;
import io.jenetics.jpx.Track;
import io.jenetics.jpx.TrackSegment;
import io.jenetics.jpx.WayPoint;
import java.util.List;

/*
    It is not an interface, but an abstract class because of the use of Metric objects.
    This way it is possible to access generalized Metric object without knowing specific type
*/
public abstract class Metric {
    /**
     * creates a string of the total data for the metric.
     * If metric is not found or is faulty, "N/A" is returned.
     * @param gpx
     * GPX object containing the data to be extracted
     * @return
     * String containing data and metric units
     */
    public abstract String display(GPX gpx);

    /**
     * Extracts and calculates each point from GPX object and makes it to Double type
     * If metric is not found or is faulty, empty List is returned.
     * @param gpx
     * GPX object containing the data to be extracted
     * @return
     * List of Double type values correlating to GPX WayPoint points
     */
    public abstract List<Double> calculateDataPoints(GPX gpx);

    /**
     * Calculates the sum total or average of the metric.
     * If metric is not found or is faulty, value that is unreal for such metric is returned.
     * @param gpx
     * GPX object containing the data to be extracted
     * @return
     * Double value that is calculated over all points
     */
    public abstract Double calculateMetricTotal(GPX gpx);

    /**
     * Marking if the metric can be displayed in a chart.
     * @return
     * Boolean value true if used in the chart
     */
    public abstract boolean isChartable();

    /**
     * Marking if the metric can be used for a goal
     * @return
     * Boolean value true if used in goals
     */
    public abstract boolean isUsedInGoals();

    /**
     * @return
     * String of the metric name
     */
    public abstract String getMetricName();

    /**
     * @return
     * String of the metric units
     */
    public abstract String getMetricUnits();

    /**
     * Extracts all the points saved in the GPX object as a List
     * @param gpx
     * GPX object containing the data to be extracted
     * @return
     * List of WayPoint objects that each contain more detailed data
     */
    public List<WayPoint> getWaypoints(GPX gpx){
        Track track = gpx.getTracks().get(0);
        TrackSegment segment = track.getSegments().get(0);
        return segment.getPoints();
    }
}
