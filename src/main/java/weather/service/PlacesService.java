package weather.service;

import weather.model.Place;
import weather.owm.ClientPlaces;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;


@Path("/place")
public class PlacesService {

        @Path("/version")
        @GET
        @Produces(MediaType.TEXT_PLAIN)
        public String getVersion(){
            return "0.01a";
        }

        @GET
        @Path("/getPlaces/{city}&{radius}")
        public ArrayList<Place> getPlace(@PathParam("city") final String city,@PathParam("radius") final double radius){

            final ClientPlaces client = new ClientPlaces(city, radius);
            ArrayList<Place> place = null;
            try {
                place = client.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return place;
        }
}
