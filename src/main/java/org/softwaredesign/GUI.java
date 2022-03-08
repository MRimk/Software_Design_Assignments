package org.softwaredesign;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class GUI extends Application {
    public static User user;
    private static final Map<String, Parent> parentMap = new HashMap<>();
    private static Stage stage;

    @Override
    public void start(Stage startStage) throws Exception {
        stage = startStage;
        initSceneParentMap();

        String startScene = chooseStartScene();
        Parent root = parentMap.get(startScene);

        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setTitle("BEAM");
        stage.setScene(scene);
        stage.show();
    }

    public static void switchScene(String fxml) {
        Parent pane = parentMap.get(fxml);
        stage.getScene().setRoot(pane);
    }

    public void initSceneParentMap() throws IOException {
        loadScene("CreateUser.fxml");
        loadScene("Dashboard.fxml");
    }

    private void loadScene(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(fxml)));
        Parent parent = loader.load();

        parentMap.put(fxml, parent);
    }

    private String chooseStartScene() {
        Gson gson = new Gson();

        try (Reader readerJSON = new FileReader("src/main/resources/user_data")) {
            user = gson.fromJson(readerJSON, User.class);
            user.pretty_print();
            return "Dashboard.fxml";
        } catch (IOException e) {
            return "CreateUser.fxml";
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}