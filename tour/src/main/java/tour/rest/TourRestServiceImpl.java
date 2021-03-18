package tour.rest;

import tour.model.Sight;
import tour.model.Weather;
import tour.repos.SightsRepository;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;


@Path("/sights")
public class TourRestServiceImpl implements TourRestService{

    private final Logger logger = Logger.getLogger("root");

    @Inject
    private SightsRepository sightDao;

    private ArrayList<Weather> weatherData;

    @Override
    public Sight[] getAllSights() {

        final Collection<Sight> allSights = sightDao.findAll().list();

        return allSights.toArray(new Sight[allSights.size()]);
    }

    @Override
    public Weather[] getAllWeathers() {
        return weatherData.toArray(new Weather[weatherData.size()]);
    }


    @Override
    public Sight[] getSights(String city, double radius) {
        Collection<Sight> allSights = sightDao.find("city",city).list();

        try {
            if(allSights.isEmpty()){
                addSight(city,radius);
                getSights(city,radius);
            }
            else {

                for(Sight sight : allSights){
                    if(sight.getRadius() >= radius){
                        allSights.remove(sight);
                    }
                }
            }

            return allSights.toArray(new Sight[allSights.size()]);

        }finally {
            //wetter abfragen
        }

    }

    @Override
    public void addSight(Sight sight) {
        sightDao.persist(sight);
    }

    @Override
    public void addSight(String city, double radius){
       // daten von places holen
       // sightDao.persist(new Sight());
    }

    @Override
    public Response changeSight(String name, Sight sight) {
        return null;
    }

    @Override
    public Response deleteSight(String name) {
        return null;
    }
}
