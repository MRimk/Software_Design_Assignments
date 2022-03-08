package org.softwaredesign;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CreateUser {
    public CreateUser() {
    }

    public void storeUserData(ActionEvent event) throws IOException {
        if (checkDataValidity()) {

        }
    }

    private boolean checkDataValidity() {
        boolean valid = true;

        if (name.getText().isEmpty() || age.getText().isEmpty() || weight.getText().isEmpty() || height.getText().isEmpty()) {
            generalError.setText("All fields must be filled");
            return false;
        }

        try {
            int parsedAge = Integer.parseInt(age.getText());
            int parsedWeight = Integer.parseInt(weight.getText());
            int parsedHeight = Integer.parseInt(height.getText());
            if (parsedAge < 9 || parsedAge > 99) {
                wrongAge.setText("Range 9-99");
                valid = false;
            }
            if (parsedWeight < 40 || parsedWeight > 200) {
                wrongWeight.setText("Range 40-200");
                valid = false;
            }
            if (parsedHeight < 60 || parsedHeight > 250) {
                wrongHeight.setText("Range 60-250");
                valid = false;
            }
        }
        catch (NumberFormatException e)
        {
            generalError.setText("Numerical Inputs Only");
            valid = false;
        }

        return valid;
    }

    @FXML
    private Button button;
    @FXML
    private TextField name;
    @FXML
    private TextField age;
    @FXML
    private TextField weight;
    @FXML
    private TextField height;
    @FXML
    private Label wrongAge;
    @FXML
    private Label wrongWeight;
    @FXML
    private Label wrongHeight;
    @FXML
    private Label generalError;
}
