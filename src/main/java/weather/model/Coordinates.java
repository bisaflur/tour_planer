package weather.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Coordinates {

    private double lat;
    private double lon;

    public Coordinates(double lat,double lon){
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    @Override
    public String toString(){
        return String.format(
                "weather.model.Coordinates [lat=%s, lon=%s ]",
                this.lat,this.lon);
    }
}
