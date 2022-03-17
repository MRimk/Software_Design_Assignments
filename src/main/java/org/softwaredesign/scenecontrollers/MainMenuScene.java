package org.softwaredesign.scenecontrollers;

import javafx.event.ActionEvent;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import org.softwaredesign.Activity;
import org.softwaredesign.GUI;
import org.softwaredesign.enumerators.Sport;
import org.softwaredesign.helpers.StringToSportHelper;

import java.io.IOException;

public class MainMenuScene {

    private void initActivity(Sport sport) throws IOException {
        String gpxPath = getGPXPath();

        GUI.setActivity(new Activity(gpxPath, sport));

        GUI.switchScene("scenes/VisualiseActivity.fxml");
    }

    private String getGPXPath() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("GPX files (*.gpx)", "*.gpx");
        fileChooser.getExtensionFilters().add(extFilter);

        return fileChooser.showOpenDialog(GUI.getStage()).getAbsolutePath();
    }

    public void sportInput(ActionEvent actionEvent) throws IOException {
        String sport = ((MenuItem) actionEvent.getSource()).getId();
        initActivity(StringToSportHelper.getSport(sport));
    }

    public void goalInput() throws IOException {
        GUI.switchScene("scenes/VisualiseGoals.fxml");
    }
}
