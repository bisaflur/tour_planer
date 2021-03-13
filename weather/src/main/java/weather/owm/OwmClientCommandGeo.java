package weather.owm;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import weather.model.Coordinates;


public class OwmClientCommandGeo extends HystrixCommand<Coordinates> {

    private String cityName;

    private final OpenWeatherMapClient owmClient = new OpenWeatherMapClient(OpenWeatherMapClient.API_KEY,"geo");

    public OwmClientCommandGeo(String cityName){

        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("geoCommands"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionTimeoutInMilliseconds(1000)));

        this.cityName = cityName;
    }

    @Override
    protected Coordinates run() throws Exception {
        return owmClient.getGeocodingCoordinates(cityName);
    }

    @Override
    protected Coordinates getFallback(){
        return new Coordinates(1337.0,2211.99);
    }
}
