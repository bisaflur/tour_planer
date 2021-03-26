package tour.rest;

import tour.model.Sight;
import tour.model.Weather;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlList;
import java.awt.*;

public interface TourRestService {

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @XmlList
    Sight[] getAllSights();

    @GET
    @Path("/weather/{city}")
    @Produces(MediaType.APPLICATION_JSON)
    Weather[] getAllWeathers(@PathParam("city")String city);

    @GET
    @Path("/{city}&{radius}")
    @Produces(MediaType.APPLICATION_JSON)
    Sight[] getSights(@PathParam("city")String city, @PathParam("radius")double radius);


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void addSight(Sight sight);

    void addSight(String city, double radius);

    @PUT
    @Path("/{city}&{radius}")
    @Consumes(MediaType.APPLICATION_JSON)
    void updateSight(@PathParam("city") String city,@PathParam("radius") double radius);

    @DELETE
    @Path("/clear")
    void deleteSights();

}
