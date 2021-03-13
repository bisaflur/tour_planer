package weather.service;

import weather.model.Coordinates;
import weather.model.WeatherData;
import weather.owm.OwmClientCommand;
import weather.owm.OwmClientCommandGeo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/weather")
public class WeatherService {

    @Path("/version")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getVersion(){
        return "0.01a";
    }

    @GET
    @Path("/getWeather/{city}")
    public ArrayList<WeatherData> getWeather(@PathParam("city") final String city){


        final OwmClientCommandGeo owmClientCommandGeo = new OwmClientCommandGeo(city);
        final Coordinates coordinates = owmClientCommandGeo.execute();

        final OwmClientCommand owmClientCommand = new OwmClientCommand(coordinates.getLat(),coordinates.getLon());
        final ArrayList<WeatherData> weatherData = owmClientCommand.execute();

        return weatherData;

        /*
        final OwmClientCommand owmClientCommand = new OwmClientCommand(city);
        final ArrayList<WeatherData> weatherData = owmClientCommand.execute();

        return weatherData;

         */
    }


}
