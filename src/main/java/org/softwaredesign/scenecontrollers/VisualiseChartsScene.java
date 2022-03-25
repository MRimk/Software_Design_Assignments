package org.softwaredesign.scenecontrollers;

import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import org.softwaredesign.GUI;
import org.softwaredesign.helpers.SportToMetricsHelper;
import org.softwaredesign.metrics.Distance;
import org.softwaredesign.metrics.Metric;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VisualiseChartsScene {
    List<Metric> sportMetrics = List.of(SportToMetricsHelper.getSportMetrics(GUI.getActivity().getSport()));

    @FXML
    ChoiceBox<String> chartChoice = new ChoiceBox<>();
    @FXML
    NumberAxis xAx;
    @FXML
    NumberAxis yAx;
    @FXML
    AreaChart<Number, Number> chartDisplay;

    public void initialize() {
        chartChoice.setValue("Choose Metric");

        for (Metric metric : sportMetrics) {
            if (metric.isChartable()) chartChoice.getItems().add(metric.getMetricName());
        }

        chartChoice.setOnAction(event -> {
            initializeChart();
        });
    }

    private void initializeChart() {
        Metric metric = stringToMetric(chartChoice.getValue());

        ArrayList<Double> dataPoints = new ArrayList<>(metric.calculateDataPoints(GUI.getActivity().getGpx()));
        Metric distanceCalculator = Distance.getInstance();
        ArrayList<Double> coveredDistancePoints = toCoveredDistance(distanceCalculator.calculateDataPoints(GUI.getActivity().getGpx()));

        xAx.setLabel(getLabelName(distanceCalculator));
        yAx.setLabel(getLabelName(metric));

        XYChart.Series<Number, Number> dataSeries = new XYChart.Series<>();
        dataSeries.setName(metric.getMetricName());
        for(int i = 0; i < dataPoints.size(); i++){
            dataSeries.getData().add(new XYChart.Data<>(coveredDistancePoints.get(i), dataPoints.get(i)));
        }

        chartDisplay.getData().clear();
        chartDisplay.getData().add(dataSeries);
        chartDisplay.setTitle(metric.getMetricName() + " Graph");
        chartDisplay.setCreateSymbols(false);
    }

    private String getLabelName(Metric labelMetric) {
        return labelMetric.getMetricName() + " (" + labelMetric.getMetricUnits() + ")";
    }

    private Metric stringToMetric(String string) {
        for (Metric metric : sportMetrics) {
            if (Objects.equals(metric.getMetricName(), string)) return metric;
        }
        return sportMetrics.get(0);
    }

    private ArrayList<Double> toCoveredDistance(List<Double> distancePoints){
        double currentTotal = 0.0;
        ArrayList<Double> resultList = new ArrayList<>();
        for(Double point : distancePoints){
            currentTotal += point;
            resultList.add(currentTotal);
        }
        return resultList;
    }
}
