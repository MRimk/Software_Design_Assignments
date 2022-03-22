package org.softwaredesign.scenecontrollers;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import org.softwaredesign.GUI;
import org.softwaredesign.metrics.Distance;
import org.softwaredesign.metrics.Elevation;
import org.softwaredesign.metrics.Metric;

import java.util.ArrayList;

public class VisualiseChartScene {

    private final Metric metric = new Elevation();
    
    @FXML
    NumberAxis xAx;
    @FXML
    NumberAxis yAx;
    @FXML
    LineChart<String, Number> chartDisplay;

    public void initialize() {
        if(!metric.isChartable()) return;

        ArrayList<Double> dataPoints = new ArrayList<>(metric.calculateDataPoints(GUI.getActivity().getGpx()));

        Distance distanceCalculator = new Distance();
        ArrayList<Double> coveredDistancePoints = distanceCalculator.coveredDistancePoints(GUI.getActivity().getGpx());

        xAx = new NumberAxis(0, coveredDistancePoints.get(coveredDistancePoints.size() - 1), 0.5); //TODO: take a look at other ways to get distance tick units
        yAx = new NumberAxis(min(dataPoints), max(dataPoints), getTickUnit(dataPoints));

        xAx.setLabel("Time (Minutes)");
        yAx.setLabel("Distance (Kilometers)");

//        chartDisplay = new LineChart(xAx, yAx);
        XYChart.Series<String, Number> dataSeries = new XYChart.Series<>();
        dataSeries.setName(metric.getMetricName() + " Graph");

        for(int i = 0; i < dataPoints.size(); i++){
            dataSeries.getData().add(new XYChart.Data<>(Double.toString(coveredDistancePoints.get(i)), dataPoints.get(i)));
        }

        chartDisplay.getData().add(dataSeries);

        chartDisplay.setCreateSymbols(false);
    }

    public Metric getMetric() {
        return metric;
    }

    private Double min(ArrayList<Double> points){
        double minPoint = 100000000;
        for(Double point : points){
            if(minPoint > point) minPoint = point;
        }
        return minPoint;
    }

    private Double max(ArrayList<Double> points){
        double maxPoint = 0;
        for(Double point: points){
            if(maxPoint < point) maxPoint = point;
        }
        return maxPoint;
    }

    private Double getTickUnit(ArrayList<Double> points){
        return (double)((int)(max(points) - min(points))/ points.size());
    }
}
