package org.softwaredesign.scenecontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.viewer.*;
import org.softwaredesign.GUI;
import org.softwaredesign.RoutePainter;
import javax.swing.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class VisualiseActivityScene {
    @FXML
    Label output;

    /**
     * Instantiate all FXML objects in the scene
     */
    public void initialize() {
        output.setText(GUI.getActivity().displayMetrics());

    }

    public void backToMainMenu() throws IOException {
        GUI.switchScene("scenes/MainMenu.fxml");
    }

    /**
     * Call a new scene to display activity charts
     * @throws IOException
    * If the scene fxml file is not found, IOException is thrown
     */
    public void visualiseCharts() throws IOException {
        GUI.switchSceneInNewWindow("scenes/VisualiseCharts.fxml");
    }

    /**
     * Create a window in which image of the map with the activity route is displayed
     */
    public void viewMap() {
        JXMapViewer mapViewer = new JXMapViewer();

        JFrame frame = new JFrame("Mapped Activity");
        frame.getContentPane().add(mapViewer);
        frame.setSize(800, 600);
        frame.setVisible(true);

        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        mapViewer.setTileFactory(tileFactory);

        List<GeoPosition> track = GUI.getActivity().getGeoPositions();
        RoutePainter routePainter = new RoutePainter(track);

        mapViewer.zoomToBestFit(new HashSet<>(track), 0.7);

        mapViewer.setOverlayPainter(routePainter);
    }
}
