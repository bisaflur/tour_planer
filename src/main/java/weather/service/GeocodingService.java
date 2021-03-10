package weather.service;

import weather.model.Coordinates;
import weather.owm.OwmClientCommandGeo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/geo")
public class GeocodingService {

    @Path("/version")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getVersion(){
        return "0.01b";
    }

    @GET
    @Path("/getCoordinates/{city}")
    public Coordinates getCoordinates(@PathParam("city") final String city){

        final OwmClientCommandGeo owmClientCommandGeo = new OwmClientCommandGeo(city);
        final Coordinates coordinates = owmClientCommandGeo.execute();

        return coordinates;
    }
}
