package service;

import client.ClientPlaces;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import model.Place;

@Path("/place")
public class PlacesService {
    public PlacesService() {
    }

    @Path("/version")
    @GET
    @Produces({"text/plain"})
    public String getVersion() {
        return "0.01a";
    }

    @GET
    @Path("/getPlaces/{city}&{radius}")
    public ArrayList<Place> getPlace(@PathParam("city") String city, @PathParam("radius") double radius) {
        ClientPlaces client = new ClientPlaces(city, radius);
        ArrayList place = null;

        try {
            place = client.run();
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        return place;
    }
}