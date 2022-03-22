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
    private static Stage stage;
    private static User user;
    private static Activity activity;

    @Override
    public void start(Stage startStage) throws Exception {
        setStage(startStage);

        Parent root = loadScene(chooseStartScene());

        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setTitle("BEAM");
        stage.setScene(scene);
        stage.show();
    }

    public static void switchScene(String fxml) throws IOException {
        stage.setScene(getScene(fxml));
    }

    public static void switchSceneInNewWindow(String fxml) throws IOException {
        Stage newStage = new Stage();
        newStage.setScene(getScene(fxml));
        newStage.show();
    }

    private static Parent loadScene(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(GUI.class.getResource(fxml)));
        return loader.load();
    }

    private static Scene getScene(String fxml) throws IOException {
        Parent pane = loadScene(fxml);
        Scene scene = new Scene(pane);
        scene.getStylesheets().add(Objects.requireNonNull(GUI.class.getResource("scenes/style.css")).toExternalForm());
        return scene;
    }

    private String chooseStartScene() {
        try {
            setUserFromFile();
            return "scenes/MainMenu.fxml";
        } catch (IOException e) {
            return "scenes/CreateUser.fxml";
        }
    }

    private static void setUserFromFile() throws FileNotFoundException {
        Gson gson = new Gson();
        user = gson.fromJson(new FileReader("src/main/resources/user_data"), User.class);
    }

    public static void setUserFromScene(User newUser){
        user = newUser;
    }
    public static void setActivity(Activity newActivity){
        activity = newActivity;
    }
    public static void setStage(Stage newStage){ stage = newStage; }
    public static Stage getStage(){
        return stage;
    }
    public static Activity getActivity(){
        return activity;
    }
    public static User getUser(){
        return user;
    }
    public static void main(String[] args) { launch(args); }
}