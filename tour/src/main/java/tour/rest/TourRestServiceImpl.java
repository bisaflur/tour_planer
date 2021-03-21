package tour.rest;

import tour.client.TourClient;
import tour.model.Sight;
import tour.model.Weather;
import tour.repos.SightsRepository;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.logging.Logger;


@Path("/sights")
public class TourRestServiceImpl implements TourRestService{

    private final Logger logger = Logger.getLogger("root");

    @Inject
    private SightsRepository sightDao;

    private ArrayList<Weather> weatherData = new ArrayList<>();

    @Override
    public Sight[] getAllSights() {

        final Collection<Sight> allSights = sightDao.findAll().list();

        return allSights.toArray(new Sight[allSights.size()]);
    }

    @Override
    public Weather[] getAllWeathers(String city) {

        ArrayList<Weather> temp = new ArrayList<>();

        for (Weather w : weatherData){
            if(w.getCity().equals(city)){
                temp.add(w);
            }
        }

        for (Weather w : temp){
            weatherData.remove(w);
        }

        return temp.toArray(new Weather[temp.size()]);
    }


    @Override
    public Sight[] getSights(String city, double radius) {

        try {

            Collection<Sight> allSights = sightDao.find("city",city).list();

            ArrayList<Sight> sights = new ArrayList<>(allSights);
            ArrayList<Sight> toRemove = new ArrayList<>();
            Sight[] response;

            boolean newRadius = false;

            if(sights.isEmpty()){
                addSight(city,radius);
                response = getSights(city,radius);
            }
            else {

                for(Sight sight : sights){
                    if( radius < sight.getRadius() ){
                        toRemove.add(sight);
                    }
                    else if( radius > sight.getRadius()){
                        newRadius = true;
                    }
                    else
                        newRadius = false;
                }

                for (Sight sight : toRemove){
                    sights.remove(sight);
                }

                for(Sight sight : sights){
                    Date today = new Date();
                    Date dateOfRequest = sight.getDateOfRequest();

                    Calendar startCalender = new GregorianCalendar();
                    startCalender.setTime(dateOfRequest);
                    Calendar endCalender = new GregorianCalendar();
                    endCalender.setTime(today);

                    int diffYear = endCalender.get(Calendar.YEAR) - startCalender.get(Calendar.YEAR);
                    int diffMonth = diffYear * 12 + endCalender.get(Calendar.MONTH) - startCalender.get(Calendar.MONTH);

                    if(diffMonth > 3){
                        //update data
                    }
                }

                if(newRadius){
                    addSight(city,radius);
                    response = getSights(city,radius);
                }
                else {
                    response = sights.toArray(new Sight[sights.size()]);
                }
            }

            return response;

        }
        finally
        {
            Weather[] temp = TourClient.consumeWeather(city);
            for (Weather w : temp)
                weatherData.add(w);
        }

    }

    @Override
    public void addSight(Sight sight) {
        sightDao.persist(sight);
    }

    @Override
    public void addSight(String city, double radius){

       // daten von places holen
        ArrayList<Sight> sights = new ArrayList<>(Arrays.asList(TourClient.consumeSights(city,radius)));
        Collection<Sight> existingSights = sightDao.find("city",city).list();
        ArrayList<Sight> toRemove = new ArrayList<>();

        for(Sight s : sights){
            for(Sight eS : existingSights){
                if(s.equals(eS)){
                    toRemove.add(s);
                }
            }
        }

        for (Sight s : toRemove)
            sights.remove(s);


        for (Sight sight : sights){
            sightDao.persist(sight);
        }

    }

    @Override
    public Response changeSight(String name, Sight sight) {
        return null;
    }

    @Override
    public void deleteSights() {
        sightDao.deleteAll();
    }
}
