package org.softwaredesign.scenecontrollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import org.softwaredesign.GUI;
import org.softwaredesign.Goal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class VisualiseGoalsScene {
    List<Label> sportColumn = new ArrayList<>();
    List<Label> metricColumn = new ArrayList<>();
    List<Label> trackerColumn = new ArrayList<>();
    List<ProgressBar> progressColumn = new ArrayList<>();
    List<Button> deleteColumn = new ArrayList<>();

    public void initialize(){
        initializeFXMLLists();
        refreshGoals();
    }
    public void displayGoalProgress(Goal newGoal, Integer index){
        Double target = newGoal.getTarget();
        Double progress = (double) Math.round(newGoal.getProgress());

        progress = (newGoal.isCompleted()) ? target : progress;

        sportColumn.get(index).setText(newGoal.getSport());
        metricColumn.get(index).setText(newGoal.getMetric());
        progressColumn.get(index).setProgress(progress / target);
        trackerColumn.get(index).setText(progress + " / " + target);
    }

    private void initializeFXMLLists() {
        sportColumn = Arrays.asList(sport0, sport1, sport2, sport3, sport4);
        metricColumn = Arrays.asList(metric0, metric1, metric2, metric3, metric4);
        trackerColumn = Arrays.asList(tracker0, tracker1, tracker2, tracker3, tracker4);
        progressColumn = Arrays.asList(progress0, progress1, progress2, progress3, progress4);
        deleteColumn = Arrays.asList(delete0, delete1, delete2, delete3, delete4);
    }

    public void deleteGoal(ActionEvent actionEvent) {
        String deleteButton = ((Button) actionEvent.getSource()).getId();
        Integer index = Integer.parseInt(deleteButton.substring(deleteButton.length() - 1));
        maxGoalError.setText("");

        GUI.getUser().deleteGoal(index);
        refreshGoals();
    }

    private void refreshGoals() {
        int numGoals = GUI.getUser().getNumGoals();
        for (int i = 0; i < numGoals; i++) {
            displayGoalProgress(GUI.getUser().getGoalList().get(i), i);
        }
        for (int i = 4; i > numGoals - 1; i--) {
            hideRow(i);
        }
    }

    private void hideRow(Integer index) {
        sportColumn.get(index).setVisible(false);
        metricColumn.get(index).setVisible(false);
        progressColumn.get(index).setVisible(false);
        trackerColumn.get(index).setVisible(false);
        deleteColumn.get(index).setVisible(false);
    }

    public void createNewGoal() throws IOException {
        if (GUI.getUser().getNumGoals() < 5) {
            GUI.switchScene("scenes/CreateGoal.fxml");
        }
        else
        {
            maxGoalError.setText("Max Goals Reached");
        }
    }

    public void backToMainMenu() throws IOException {
        GUI.switchScene("scenes/MainMenu.fxml");
    }

    @FXML
    Label sport0, sport1, sport2, sport3, sport4;
    @FXML
    Label metric0, metric1, metric2, metric3, metric4;
    @FXML
    Label tracker0, tracker1, tracker2, tracker3, tracker4;
    @FXML
    ProgressBar progress0, progress1, progress2, progress3, progress4;
    @FXML
    Button delete0, delete1, delete2, delete3, delete4;
    @FXML
    Label maxGoalError;
}

