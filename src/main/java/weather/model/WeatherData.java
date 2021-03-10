package weather.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class WeatherData{

    private float temperatureCelsiusMax;
    private float temperatureCelsiusMin;
    private String clouds;
    private Date timeStamp;


    public WeatherData(float temperatureCelsiusMax, float temperatureCelsiusMin, String clouds, Date timeStamp){
        this.temperatureCelsiusMax = temperatureCelsiusMax;
        this.temperatureCelsiusMin = temperatureCelsiusMin;
        this.timeStamp = timeStamp;
        this.clouds = clouds;
    }

    public WeatherData(){
        super();
    }

    public float getTemperatureCelsiusMax() {
        return temperatureCelsiusMax;
    }

    public void setTemperatureCelsiusMax(float temperatureCelsiusMax) {
        this.temperatureCelsiusMax = temperatureCelsiusMax;
    }

    public float getTemperatureCelsiusMin() {
        return temperatureCelsiusMin;
    }

    public void setTemperatureCelsiusMin(float temperatureCelsiusMin) {
        this.temperatureCelsiusMin = temperatureCelsiusMin;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getClouds() {
        return clouds;
    }

    public void setClouds(String clouds) {
        this.clouds = clouds;
    }
    @Override
    public String toString()
    {
        return String.format(
                "weather.model.WeatherData [temperatureCelsiusMax=%s, temperatureCelsiusMin=%s, clouds=%s, timeStamp=%s]",
                 temperatureCelsiusMax, temperatureCelsiusMin, clouds, timeStamp);
    }
}
