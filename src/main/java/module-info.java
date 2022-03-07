module Software_Design_Assignments {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.google.gson;
    requires java.xml;
    requires io.jenetics.jpx;

    opens org.softwaredesign to javafx.fxml, com.google.gson;
    exports org.softwaredesign;
}