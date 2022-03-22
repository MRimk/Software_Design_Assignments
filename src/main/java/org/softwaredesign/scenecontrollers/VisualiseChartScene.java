package org.softwaredesign.scenecontrollers;

import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import org.softwaredesign.GUI;
import org.softwaredesign.metrics.Distance;
import org.softwaredesign.metrics.Elevation;
import org.softwaredesign.metrics.Metric;
import org.softwaredesign.metrics.Speed;

import java.util.ArrayList;

public class VisualiseChartScene {

    private final Metric metric = new Speed();
    
    @FXML
    NumberAxis xAx;
    @FXML
    NumberAxis yAx;
    @FXML
    AreaChart<Number, Number> chartDisplay;

    public void initialize() {
        ArrayList<Double> dataPoints = new ArrayList<>(metric.calculateDataPoints(GUI.getActivity().getGpx()));
        Distance distanceCalculator = new Distance();
        ArrayList<Double> coveredDistancePoints = distanceCalculator.coveredDistancePoints(GUI.getActivity().getGpx());

        xAx.setLabel(getLabelName(distanceCalculator));
        yAx.setLabel(getLabelName(metric));

        XYChart.Series<Number, Number> dataSeries = new XYChart.Series<>();
        dataSeries.setName(metric.getMetricName());
        for(int i = 0; i < dataPoints.size(); i++){
            dataSeries.getData().add(new XYChart.Data<>(coveredDistancePoints.get(i), dataPoints.get(i)));
        }

        chartDisplay.getData().add(dataSeries);
        chartDisplay.setTitle(metric.getMetricName() + " Graph");
        chartDisplay.setCreateSymbols(false);
    }

    public Metric getMetric() {
        return metric;
    }

    private String getLabelName(Metric labelMetric){
        return labelMetric.getMetricName() + " (" + labelMetric.getMetricUnits() + ")";
    }
}
