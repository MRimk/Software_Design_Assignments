package softwaredesign;
import org.alternativevision.gpx.GPXParser;
import org.alternativevision.gpx.beans.GPX;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Activity {
    private GPX gpx;
    private SportTypes sport;
    private final User user;

    public Activity(String gpxPath, SportTypes sport, User user)
            throws IOException, ParserConfigurationException, SAXException {
        this.user = user;
        this.sport = sport;
        GPXParser parser = new GPXParser();
        FileInputStream inputStream = new FileInputStream(gpxPath);
        gpx = parser.parseGPX(inputStream);
    }
}
