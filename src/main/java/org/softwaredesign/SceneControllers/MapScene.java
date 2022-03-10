package org.softwaredesign.SceneControllers;

import com.dlsc.gmapsfx.GoogleMapView;
import com.dlsc.gmapsfx.MapComponentInitializedListener;
import com.dlsc.gmapsfx.javascript.object.GoogleMap;
import com.dlsc.gmapsfx.javascript.object.LatLong;
import com.dlsc.gmapsfx.javascript.object.MapOptions;
import com.dlsc.gmapsfx.javascript.object.MapTypeIdEnum;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.softwaredesign.GUI;

public class MapScene implements Initializable, MapComponentInitializedListener {

    @FXML
    private GoogleMapView mapView;

    private GoogleMap map;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       mapView.addMapInitializedListener(this);
    }

    @Override
    public void mapInitialized() {
        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();

        LatLong startPosition = new LatLong(GUI.activity.getCoordinates().get(0).get(0), GUI.activity.getCoordinates().get(0).get(1));

        mapOptions.center(startPosition)
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(true)
                .zoom(12);

        map = mapView.createMap(mapOptions);
    }
}