package softwaredesign;
//import org.alternativevision.gpx.GPXParser;
//import org.alternativevision.gpx.beans.GPX;
//import tomc.gpx.*;

import io.jenetics.jpx.*;

import io.jenetics.jpx.GPX;
import org.xml.sax.SAXException;
import softwaredesign.metrics.Metric;

import javax.xml.parsers.ParserConfigurationException;

import java.io.IOException;


public class Activity {
    private GPX gpx;
    private SportTypes sport;
    private final User user;

    public Activity(String gpxPath, SportTypes sport, User user)
            throws IOException, ParserConfigurationException, SAXException {
        this.user = user;
        this.sport = sport;
        gpx = GPX.read(gpxPath);
    }

    public String displayMetrics(){
        Metric[] metricsList = SportToMetricsHelper.getSportMetrics(sport);
        StringBuilder metricsText = new StringBuilder();
        for(Metric metric : metricsList){
            System.out.println(metric.display(gpx));
            metricsText.append(metric.display(gpx));
            metricsText.append("\n");
        }
        return metricsText.toString();
    }
}
