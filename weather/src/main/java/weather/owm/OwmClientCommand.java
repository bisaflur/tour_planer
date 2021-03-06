package weather.owm;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import weather.model.WeatherData;

import java.util.ArrayList;
import java.util.Date;

public class OwmClientCommand extends HystrixCommand<ArrayList<WeatherData>> {

    private String cityName;
    private double lat,lon;

    private final OpenWeatherMapClient owmClient = new OpenWeatherMapClient(OpenWeatherMapClient.API_KEY,"weather");

    public OwmClientCommand(String cityName){

        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("weatherCommands"))
        .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
        .withExecutionTimeoutInMilliseconds(500)));

        this.cityName = cityName;
    }

    public OwmClientCommand(double lat, double lon){

        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("weatherCommands"))
        .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
        .withExecutionTimeoutInMilliseconds(500)));

        this.lat = lat;
        this.lon = lon;
    }


    @Override
    protected ArrayList<WeatherData> run() throws Exception {
        return owmClient.getWeatherForecast(lat,lon);
        //return owmClient.getWeatherForecast(cityName);
    }

    @Override
    protected ArrayList<WeatherData> getFallback(){
        ArrayList<WeatherData> weatherData = new ArrayList<>();
        weatherData.add(new WeatherData(1337f,1337f,"bewölkt",new Date()));
        return weatherData;
    }
}
