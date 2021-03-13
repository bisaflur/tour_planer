package client;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import model.Place;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientPlaces {

    private double radius;
    private String city;

    public static final String API_KEY = "7QFa8TNpvzCNryNLgwynUA4IO12WPDbA";

    private static final String API_URL = "https://www.mapquestapi.com/search/v2/radius";

    public ClientPlaces(String city, double radius){
        this.city = city;
        this.radius = radius;
    }

    public ArrayList<Place> run() throws Exception {
        return makeRequest(city,radius);
    }

    private ArrayList<Place> makeRequest(String city, double radius) throws IOException, ParseException {
        ArrayList<Place> places = new ArrayList<>();
        URL url = new URL(urlBuilder(city, radius));

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();

        if (responseCode != 200)
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        else {
            String line = "";
            Scanner sc = new Scanner(con.getInputStream());
            while (sc.hasNext()) {
                line += sc.nextLine();
            }
            sc.close();
            JSONParser parse = new JSONParser();
            JSONObject jsonO = (JSONObject) parse.parse(line);
            JSONArray jsonArray = (JSONArray) jsonO.get("searchResults");
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.size(); i++) //Places in ArrayList speichern
                {
                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                    JSONObject ls = (JSONObject) jsonObject.get("fields");
                    String name = (String) jsonObject.get("name");
                    String address = (String) ls.get("address");
                    double lat = (double) ls.get("lat");
                    double lon = (double) ls.get("lng");
                    places.add(new Place(name, address, lat, lon));
                }
                return places;
            }
            return fall();
        }
    }

    private ArrayList<Place> fall(){
        ArrayList<Place> pl = new ArrayList<>();
        Place place = new Place("","",0.0, 0.0);
        pl.add(place);
        return pl;
    }

    private String urlBuilder(String city, double radius){
        return API_URL + "?origin=" + city + "&radius=" + radius +
                "&maxMatches=10&ambiguities=ignore&hostedData=mqap.ntpois|group_sic_code=?|999333&outFormat=json&key="+
                API_KEY;
    }
}