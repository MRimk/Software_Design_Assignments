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

    /**
     * Create a new activity from a .gpx file path and a sport type
     * @param sport
     * Sport enumerator object that represents the type
     * @throws IOException
     * If the activity visualisation scene fxml file is not found, IOException is thrown
     */
    private void initActivity(Sport sport) throws IOException {
        String gpxPath = getGPXPath();

        if (gpxPath != null) {
            GUI.setActivity(new Activity(gpxPath, sport));
        }
        else {
            return;
        }

        GUI.switchScene("scenes/VisualiseActivity.fxml");
    }

    /**
     * Get the selected file's path to extract the data from
     * @return
     * String path of the .gpx file
     */
    private String getGPXPath() {
        try {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter =
                    new FileChooser.ExtensionFilter("GPX files (*.gpx)", "*.gpx");
            fileChooser.getExtensionFilters().add(extFilter);

            return fileChooser.showOpenDialog(GUI.getStage()).getAbsolutePath();
        }
        catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * Selection of the sport and initialisation of a new activity for the selected sport
     * @param actionEvent
     * Menu item selection event where a sport type is selected
     * @throws IOException
     * If the menu item that was selected does not exist anymore, IOException is thrown
     */
    public void sportInput(ActionEvent actionEvent) throws IOException {
        String sport = ((MenuItem) actionEvent.getSource()).getId();
        initActivity(StringToSportHelper.getSport(sport));
    }

    /**
     * Display goals scene
     * @throws IOException
     * If the scene fxml file is not found, IOException is thrown
     */
    public void goalInput() throws IOException {
        GUI.switchScene("scenes/VisualiseGoals.fxml");
    }
}
