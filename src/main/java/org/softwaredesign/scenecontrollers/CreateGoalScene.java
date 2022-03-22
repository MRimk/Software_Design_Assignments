package org.softwaredesign.scenecontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.softwaredesign.GUI;
import org.softwaredesign.Goal;
import org.softwaredesign.enumerators.Sport;
import org.softwaredesign.helpers.SportToMetricsHelper;
import org.softwaredesign.helpers.StringToSportHelper;
import org.softwaredesign.metrics.Metric;
;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class CreateGoalScene {
    @FXML
    ChoiceBox<String> sportChoice = new ChoiceBox<>();
    @FXML
    ChoiceBox<String> metricChoice = new ChoiceBox<>();
    @FXML
    Label sportError;
    @FXML
    Label metricError;
    @FXML
    TextField target;
    @FXML
    Label targetError;

    public void initialize() {
        sportChoice.setValue("Sport");
        metricChoice.setValue("Metric");
        sportChoice.getItems().addAll("Running", "Cycling", "Swimming");

        sportChoice.setOnAction(event -> {
            updateMetricChoice();
        });
    }

    public void createGoal() throws IOException {
        if (checkDataValidity()) {
            GUI.getUser().addGoal(new Goal(sportChoice.getValue(), Double.parseDouble(target.getText()), metricChoice.getValue()));
            GUI.getUser().saveUserData();
            backToGoals();
        }
    }

    private void updateMetricChoice() {
        metricChoice.setValue("Metric");

        metricChoice.getItems().clear();

        Sport sport = StringToSportHelper.getSport(sportChoice.getValue());
        List<Metric> metricList = List.of(SportToMetricsHelper.getSportMetrics(sport));

        for (Metric metric : metricList) {
           if (metric.isUsedInGoals()) metricChoice.getItems().add(metric.getMetricName());
        }
    }

    private boolean checkDataValidity() {
        if (Objects.equals(sportChoice.getValue(), "Sport")) {
            sportError.setText("Choose sport");
            return false;
        }
        else {
            sportError.setText("");
        }

        if (Objects.equals(metricChoice.getValue(),"Metric")) {
            metricError.setText("Choose metric");
            return false;
        }
        else {
            metricError.setText("");
        }

        if (target.getText().isEmpty()) {
            targetError.setText("Fill target");
            return false;
        }
        else {
            targetError.setText("");
        }

        try {
            double parsedTarget = Double.parseDouble(target.getText());
            if(parsedTarget <= 0) {
                targetError.setText("Bigger than 0");
                return false;
            }
            else
            {
                targetError.setText("");
            }
        }
        catch(NumberFormatException e) {
            targetError.setText("Numerical inputs only");
            return false;
        }

        return true;
    }

    public void backToGoals() throws IOException {
        GUI.switchScene("scenes/VisualiseGoals.fxml");
    }
}
