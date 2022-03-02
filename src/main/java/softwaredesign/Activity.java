package softwaredesign;
//import me.himanshusoni.gpxparser

public class Activity {
//    private GPX gpx
    private SportTypes sport;
    private final User user;

    public Activity(String gpxPath, SportTypes sport, User user){
        this.user = user;
        this.sport = sport;
        getGPX(gpxPath);
    }

    private static void getGPX(String path){
        //this is the place to parse GPX file and build the object
    }
}
