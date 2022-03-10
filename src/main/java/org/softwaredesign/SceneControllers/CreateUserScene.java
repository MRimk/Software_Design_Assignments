package org.softwaredesign.SceneControllers;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.softwaredesign.GUI;
import org.softwaredesign.StringToGenderHelper;
import org.softwaredesign.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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

    public void initUser() throws IOException {
        Gson gson = new Gson();

        if (checkDataValidity()) {
            GUI.user = new User(
                    name.getText(),
                    Integer.valueOf(age.getText()),
                    Double.valueOf(weight.getText()),
                    Integer.valueOf(height.getText()),
                    StringToGenderHelper.getGender(gender.getText()));

            saveUserData(gson.toJson(GUI.user));
            GUI.switchScene("MainMenu.fxml");
        }
    }

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

    private static void saveUserData(String compiledJSON) {
        try {
            File myObj = new File("src/main/resources/user_data");
            if (!myObj.createNewFile()) {
                System.out.println("File already exist");
            }
        }
        catch (IOException e) {
            System.out.println("Could not create file 'user_data'");
        }

        try (FileWriter writer = new FileWriter("src/main/resources/user_data")) {
            writer.write(compiledJSON);
        }
        catch (IOException e) {
            System.out.println("Could not write to file 'user_data'");
        }
    }
}
