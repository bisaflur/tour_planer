package tour.client;

import tour.model.Sight;
import tour.model.Weather;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

public class TourClient {

    private static final String WEATHER_API = "http://localhost:9080/weather/getWeather/";
    //private static final String WEATHER_API = "http://weather:8080/weather/getWeather/";
    private static final String PLACES_API = "http://localhost:9090/place/getPlaces/";
    //private static final String PLACES_API = "http://place:8080/place/getPlaces/";


    public static Weather[] consumeWeather(String city) {
        Client client = ClientBuilder.newClient();
        Response response = client.target(WEATHER_API + city).request().get();
        Weather[] weathers = response.readEntity(Weather[].class);

        for (Weather weather : weathers)
            weather.setCity(city);

        response.close();
        client.close();
        return weathers;
    }

    public static Sight[] consumeSights(String city, double radius) {
        Client client = ClientBuilder.newClient();
        Response response = client.target(PLACES_API + city + "&" + radius).request().get();
        Sight[] sights = response.readEntity(Sight[].class);

        for (Sight sight : sights) {
            sight.setCity(city);
            sight.setRadius(radius);
        }

        response.close();
        client.close();
        return sights;
    }
}
