package org.softwaredesign.scenecontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.softwaredesign.GUI;

import java.io.IOException;

public class VisualiseActivityScene {
    @FXML
    Label output;

    public void initialize() {
        output.setText(GUI.getActivity().displayMetrics());
    }

    public void backToMainMenu() throws IOException {
        GUI.switchScene("MainMenu.fxml");
    }
}
