package org.softwaredesign.scenecontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.softwaredesign.GUI;
import org.softwaredesign.helpers.StringToGenderHelper;
import org.softwaredesign.User;
import java.io.IOException;
import java.util.ArrayList;

public class CreateUserScene {
    @FXML
    private TextField name;
    @FXML
    private TextField age;
    @FXML
    private TextField weight;
    @FXML
    private TextField height;
    @FXML
    private TextField gender;
    @FXML
    private Label wrongAge;
    @FXML
    private Label wrongWeight;
    @FXML
    private Label wrongHeight;
    @FXML
    private Label wrongGender;
    @FXML
    private Label generalError;

    /**
     * Initialises the new user from the input data
     * @throws IOException
     * If the main menu fxml file is not found, IOException is thrown
     */
    public void initUser() throws IOException {
        if (checkDataValidity()) {
            GUI.setUserFromScene(new User(
                    name.getText(),
                    Integer.valueOf(age.getText()),
                    Double.valueOf(weight.getText()),
                    Integer.valueOf(height.getText()),
                    StringToGenderHelper.getGender(gender.getText()),
                    new ArrayList<>()));

            GUI.getUser().saveUserData();
            GUI.switchScene("scenes/MainMenu.fxml");
        }
    }

    /**
     * Checks if the user input data is reasonable
     * @return
     * True = data is valid
     */
    private boolean checkDataValidity() {
        boolean valid = true;

        if (name.getText().isEmpty() || age.getText().isEmpty() || weight.getText().isEmpty() || height.getText().isEmpty() || gender.getText().isEmpty()) {
            generalError.setText("All fields must be filled");
            return false;
        }
        else
        {
            generalError.setText("");
        }

        try {
            int parsedAge = Integer.parseInt(age.getText());
            int parsedWeight = Integer.parseInt(weight.getText());
            int parsedHeight = Integer.parseInt(height.getText());
            if (parsedAge < 9 || parsedAge > 99) {
                wrongAge.setText("Range 9-99");
                valid = false;
            }
            else
            {
                wrongAge.setText("");
            }
            if (parsedWeight < 35 || parsedWeight > 250) {
                wrongWeight.setText("Range 40-250");
                valid = false;
            }
            else
            {
                wrongWeight.setText("");
            }
            if (parsedHeight < 60 || parsedHeight > 250) {
                wrongHeight.setText("Range 60-250");
                valid = false;
            }
            else
            {
                wrongHeight.setText("");
            }
            if (StringToGenderHelper.getGender(gender.getText()) != null) {
                wrongGender.setText(" ");
            }
            else
            {
                wrongGender.setText("Must be M or F");
                valid = false;
            }
        }
        catch (NumberFormatException e)
        {
            generalError.setText("Age | Weight | Height   Must Be Numerical Inputs");
            valid = false;
        }

        return valid;
    }
}
