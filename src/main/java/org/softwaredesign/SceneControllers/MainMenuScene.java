package org.softwaredesign.SceneControllers;

import javafx.stage.FileChooser;
import org.softwaredesign.Activity;
import org.softwaredesign.GUI;
import org.softwaredesign.SportTypes;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class MainMenuScene {

    private void initActivity(SportTypes sport) throws IOException, ParserConfigurationException, SAXException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("GPX files (*.gpx)", "*.gpx");
        fileChooser.getExtensionFilters().add(extFilter);
        String GPXPath = fileChooser.showOpenDialog(GUI.stage).getAbsolutePath();

        GUI.activity = new Activity(GPXPath, sport);

        GUI.switchScene("VisualizeActivity.fxml");
    }

    public void runningInput() throws IOException, ParserConfigurationException, SAXException {
        initActivity(SportTypes.RUNNING);
    }

    public void swimmingInput() throws IOException, ParserConfigurationException, SAXException {
        initActivity(SportTypes.SWIMMING);
    }

    public void cyclingInput() throws IOException, ParserConfigurationException, SAXException {
        initActivity(SportTypes.CYCLING);
    }
}
