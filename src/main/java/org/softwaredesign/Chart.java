package org.softwaredesign;

import javafx.scene.chart.*;
import org.softwaredesign.metrics.Distance;
import org.softwaredesign.metrics.Metric;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Chart extends Application {
        private final Metric metric;
        private final String sport;

        public Chart(String sport, Metric type) {
            this.sport = sport;
            this.metric = type;
        }

        public void start(Stage stage){
            if(!metric.isChartable()) return;

            ArrayList<Double> dataPoints = new ArrayList<>(metric.calculateDataPoints(GUI.getActivity().getGpx()));

            Distance distanceCalculator = new Distance();
            ArrayList<Double> coveredDistancePoints = distanceCalculator.coveredDistancePoints(GUI.getActivity().getGpx());

            NumberAxis xAx = new NumberAxis(0, coveredDistancePoints.get(coveredDistancePoints.size() - 1), 0.5); //TODO: take a look at other ways to get distance tick units
            NumberAxis yAx = new NumberAxis(min(dataPoints), max(dataPoints), getTickUnit(dataPoints));

            xAx.setLabel("Time (Minutes)");
            yAx.setLabel("Distance (Kilometers)");

            LineChart <Number, Number> chartDisplay = new LineChart<>(xAx, yAx);
            XYChart.Series<Number, Number> dataSeries = new XYChart.Series<>();
            dataSeries.setName(metric.getMetricName() + " Graph");



            for(int i = 0; i < dataPoints.size(); i++){
                dataSeries.getData().add(new XYChart.Data<>(i, dataPoints.get(i)));
            }
            chartDisplay.getData().add(dataSeries);

            Group rootDisplay = new Group(chartDisplay);
            Scene visualiseActivityScene = new Scene(rootDisplay, 500, 400);
            stage.setScene(visualiseActivityScene);
            stage.setTitle("GPX Chart Visualization");
            stage.show();

        }

        public static void chartMain(String[] name){
            launch(name);
        }

        public String getSport() {
            return sport;
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
