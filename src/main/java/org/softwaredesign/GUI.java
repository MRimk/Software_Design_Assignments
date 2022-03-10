package org.softwaredesign;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;


public class GUI extends Application {
    public static Stage stage;
    public static User user;
    public static Activity activity;

    @Override
    public void start(Stage startStage) throws Exception {
        stage = startStage;

        Parent root = loadScene(chooseStartScene());

        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setTitle("BEAM");
        stage.setScene(scene);
        stage.show();
    }

    public static void switchScene(String fxml) throws IOException {
        Parent pane = loadScene(fxml);
        stage.setScene(new Scene(pane));
    }

    private static Parent loadScene(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(GUI.class.getResource(fxml)));
        return loader.load();
    }

    private String chooseStartScene() {
        try {
            loadUser();
            return "MainMenu.fxml";
        } catch (IOException e) {
            return "CreateUser.fxml";
        }
    }

    private void loadUser() throws FileNotFoundException {
        Gson gson = new Gson();
        user = gson.fromJson(new FileReader("src/main/resources/user_data"), User.class);
    }

    public static void main(String[] args) {
        launch(args);
    }
}