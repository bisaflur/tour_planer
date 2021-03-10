package weather.owm;

import weather.model.Coordinates;
import weather.model.WeatherData;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;



public class OpenWeatherMapClient {

    public static final String API_KEY = "a7fb797d85341a6c693c516cb748ae28";

    private static final String OWM_API_2_5_URL = "http://api.openweathermap.org/data/2.5/forecast";//"http://api.openweathermap.org/data/2.5/forecast";
    private static final String OWM_API_1_0_URL = "http://api.openweathermap.org/geo/1.0/direct";
    private static final String OWM_API_2_5_URL_ONECALL = "https://api.openweathermap.org/data/2.5/onecall";

    private Client client;
    private WebTarget webTarget;

    public OpenWeatherMapClient(String apiKey){
        client = ClientBuilder.newClient();
        webTarget = client.target(getURL()).queryParam("appid",apiKey);
    }
    public OpenWeatherMapClient(String apiKey,String service){
        client = ClientBuilder.newClient();
        webTarget = client.target(getURL(service)).queryParam("appid",apiKey);
    }

    private String getURL(){
            return OWM_API_2_5_URL;
    }

    private String getURL(String service){
        if(service.equals("weather"))
            return OWM_API_2_5_URL_ONECALL;
        else if(service.equals("geo"))
            return OWM_API_1_0_URL;
        else
            return null;
    }

    public ArrayList<WeatherData> getWeatherForecast(double lat, double lon){
        return requestWeatherForecast(lat,lon);
    }

    public ArrayList<WeatherData> requestWeatherForecast(double lat, double lon){
        final Response response = webTarget.queryParam("lat",lat).queryParam("lon",lon).queryParam("units","metric").queryParam("exclude","current,minutely,hourly,alerts").request(MediaType.APPLICATION_JSON).get();
        final JsonObject jsonObject = Json.createReader(response.readEntity(InputStream.class)).readObject();
        return jsonResponseToWeatherDataOnecall(jsonObject);
    }


    public ArrayList<WeatherData> getWeatherForecast(String cityName){
        return requerstWeatherForecast(cityName);
    }

    private ArrayList<WeatherData> requerstWeatherForecast(String cityName){
        final Response response = webTarget.queryParam("q",cityName).queryParam("mode","json").queryParam("units","metric").request(MediaType.APPLICATION_JSON).get();
        final JsonObject jsonObject = Json.createReader(response.readEntity(InputStream.class)).readObject();
        return jsonResponseToWeatherData(jsonObject);
    }

    private ArrayList<WeatherData> jsonResponseToWeatherData(JsonObject jsonObject){

        ArrayList<WeatherData> weatherData = new ArrayList<>();

        JsonArray list = jsonObject.getJsonArray("list");
        for (JsonValue value : list){
            JsonObject mainData = value.asJsonObject().getJsonObject("main");
            final float tempCelsiusMax = (float) mainData.getJsonNumber("temp_max").doubleValue();
            final float tempCelsiusMin = (float) mainData.getJsonNumber("temp_min").doubleValue();

            String clouds = "";
            JsonArray weather = value.asJsonObject().getJsonArray("weather");
            for (JsonValue weatherValue:weather){
                clouds = weatherValue.asJsonObject().getJsonString("description").getString();
            }


            weatherData.add(new WeatherData(tempCelsiusMax,tempCelsiusMin,clouds,new Date()));
        }

        return weatherData;
    }

    private ArrayList<WeatherData> jsonResponseToWeatherDataOnecall(JsonObject jsonObject){
        ArrayList<WeatherData> weatherData = new ArrayList<>();

        Date currentDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);

        JsonArray list = jsonObject.getJsonArray("daily");
        for (JsonValue value : list){
            JsonObject mainData = value.asJsonObject().getJsonObject("temp");
            final float tempCelsiusMax = (float) mainData.getJsonNumber("max").doubleValue();
            final float tempCelsiusMin = (float) mainData.getJsonNumber("min").doubleValue();

            String clouds = "";
            JsonArray weather = value.asJsonObject().getJsonArray("weather");
            for (JsonValue weatherValue:weather){
                clouds = weatherValue.asJsonObject().getJsonString("description").getString();
            }

            Date date = c.getTime();

            weatherData.add(new WeatherData(tempCelsiusMax,tempCelsiusMin,clouds,date));

            c.add(Calendar.DAY_OF_MONTH,1);
        }

        return weatherData;
    }



    public Coordinates getGeocodingCoordinates(String cityName){
        return requestGeocodingCoordinates(cityName);
    }

    private Coordinates requestGeocodingCoordinates(String cityName){
        final Response response = webTarget.queryParam("q",cityName).queryParam("limit","1").request(MediaType.APPLICATION_JSON).get();
        final JsonArray jsonArray = Json.createReader(response.readEntity(InputStream.class)).readArray();
        return jsonResponseToGeocodingCoordinates(jsonArray);
    }

    private Coordinates jsonResponseToGeocodingCoordinates(JsonArray jsonArray){


        JsonObject jo = jsonArray.getJsonObject(0);

        double lat = jo.getJsonNumber("lat").doubleValue();
        double lon = jo.getJsonNumber("lon").doubleValue();


        return new Coordinates(lat,lon);
    }
}
