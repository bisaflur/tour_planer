package tour.model;

import java.util.Date;
import java.util.Objects;
import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


public class Weather {


    private String clouds;

    private double temperatureMax;

    private double temperatureMin;

    private Date date;

    public Weather(){
        super();
    }

    @JsonbCreator
    public Weather(
            @JsonbProperty("clouds") String clouds,
            @JsonbProperty("temperatureCelsiusMax") double temperatureMax,
            @JsonbProperty("temperatureCelsiusMin") double temperatureMin,
            @JsonbProperty("timeStamp") Date date
    ){
        this.clouds = clouds;
        this.temperatureMax = temperatureMax;
        this.temperatureMin = temperatureMin;
        this.date = date;
    }



    public String getClouds() {
        return clouds;
    }

    public void setClouds(String clouds) {
        this.clouds = clouds;
    }

    public double getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(double temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public double getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(double temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString(){
        return String.format("Weather [description='%s', temperatureMax='%d', temperatureMin='%d', date='%s']",clouds,temperatureMax,temperatureMin,date);
    }

    @Override
    public int hashCode(){
        return Objects.hash(clouds,temperatureMax,temperatureMin,date);
    }

    @Override
    public boolean equals(final Object obj){

        if (this == obj)
            return true;
        if (!(obj instanceof Weather))
            return false;
        final Weather other = (Weather) obj;
        return Objects.equals(clouds,other.clouds) && Objects.equals(temperatureMax,other.temperatureMax)&& Objects.equals(temperatureMin,other.temperatureMin)&& Objects.equals(date,other.date);
    }
}
