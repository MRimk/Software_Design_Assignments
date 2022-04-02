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
    private List<Label> sportColumn = new ArrayList<>();
    private List<Label> metricColumn = new ArrayList<>();
    private List<Label> trackerColumn = new ArrayList<>();
    private List<ProgressBar> progressColumn = new ArrayList<>();
    private List<Button> deleteColumn = new ArrayList<>();

    /**
     * Instantiate all FXML objects in the scene
     */
    public void initialize(){
        initializeFXMLLists();
        refreshGoals();
    }

    /**
     * Show a row of a newly tracked goal progress
     * @param newGoal
     * A new Goal object that is added to the display
     * @param index
     * Index of the new goal row
     */
    public void displayGoalProgress(Goal newGoal, Integer index){
        Double target = newGoal.getTarget();
        Double progress = (double) Math.round(newGoal.getProgress());

        progress = Boolean.TRUE.equals((newGoal.isCompleted())) ? target : progress;

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

    /**
     * Function used to remove the goal from the existing goal list
     * @param actionEvent
     * Button click event with which the goal is deleted
     */
    public void deleteGoal(ActionEvent actionEvent) {
        String deleteButton = ((Button) actionEvent.getSource()).getId();
        Integer index = Integer.parseInt(deleteButton.substring(deleteButton.length() - 1));
        maxGoalError.setText("");

        GUI.getUser().deleteGoal(index);
        refreshGoals();
    }

    /**
     * Shows all existing goals
     */
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

    /**
     * Switches to the create goal scene if there is space for more goals
     * @throws IOException
     * If the scene fxml file is not found, IOException is thrown
     */
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
    Label sport0;
    @FXML
    Label sport1;
    @FXML
    Label sport2;
    @FXML
    Label sport3;
    @FXML
    Label sport4;
    @FXML
    Label metric0;
    @FXML
    Label metric1;
    @FXML
    Label metric2;
    @FXML
    Label metric3;
    @FXML
    Label metric4;
    @FXML
    Label tracker0;
    @FXML
    Label tracker1;
    @FXML
    Label tracker2;
    @FXML
    Label tracker3;
    @FXML
    Label tracker4;
    @FXML
    ProgressBar progress0;
    @FXML
    ProgressBar progress1;
    @FXML
    ProgressBar progress2;
    @FXML
    ProgressBar progress3;
    @FXML
    ProgressBar progress4;
    @FXML
    Button delete0;
    @FXML
    Button delete1;
    @FXML
    Button delete2;
    @FXML
    Button delete3;
    @FXML
    Button delete4;
    @FXML
    Label maxGoalError;
}

