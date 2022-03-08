module Software_Design_Assignments {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.google.gson;
    requires java.xml;
    requires io.jenetics.jpx;

    opens org.softwaredesign to javafx.fxml, com.google.gson;
    exports org.softwaredesign;
    exports org.softwaredesign.metrics;
    opens org.softwaredesign.metrics to com.google.gson, javafx.fxml;
    exports org.softwaredesign.SceneControllers;
    opens org.softwaredesign.SceneControllers to com.google.gson, javafx.fxml;
}