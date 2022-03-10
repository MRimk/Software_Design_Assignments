package org.softwaredesign.SceneControllers;

import javafx.event.ActionEvent;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import org.softwaredesign.Activity;
import org.softwaredesign.GUI;
import org.softwaredesign.SportTypes;
import org.softwaredesign.Helpers.StringToSportHelper;

import java.io.IOException;

public class MainMenuScene {

    private void initActivity(SportTypes sport) throws IOException {
        String GPXPath = getGPXPath();

        GUI.activity = new Activity(GPXPath, sport);

        GUI.switchScene("VisualizeActivity.fxml");
    }

    private String getGPXPath() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("GPX files (*.gpx)", "*.gpx");
        fileChooser.getExtensionFilters().add(extFilter);

        return fileChooser.showOpenDialog(GUI.stage).getAbsolutePath();
    }

    public void sportInput(ActionEvent actionEvent) throws IOException {
        String sport = ((MenuItem) actionEvent.getSource()).getId();
        initActivity(StringToSportHelper.getSport(sport));
    }
}
