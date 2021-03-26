package client;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import model.Place;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientPlaces {

    private double radius;
    private String city;

    public static final String API_KEY = "1XAEDQC5MWMR3XHEEKSR2UQWTC5I50LH3NA0VJCCCGQCYTQH";

    private static final String API_URL = "https://api.foursquare.com/v2/venues/explore?client_id=";

    private static final String CLIENT_ID = "T2OCNN2Q0UTLKJGWAS2LR0EZIMTUWHYVIJMTBQZCUKA43TBN";

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
            Scanner sc = new Scanner(con.getInputStream(), StandardCharsets.UTF_8);
            while (sc.hasNext()) {
                line += sc.nextLine();
            }
            sc.close();

            JSONParser parse = new JSONParser();
            JSONObject jsonResponse = (JSONObject) parse.parse(line);
            JSONObject jsonObject = (JSONObject) jsonResponse.get("response");
            JSONArray groups = (JSONArray) jsonObject.get("groups");

            if (groups != null) {
                    JSONObject groupsObject = (JSONObject) groups.get(0);
                    JSONArray items = (JSONArray) groupsObject.get("items");
                for (Object item : items) {
                    JSONObject itemsObject = (JSONObject) item;
                    JSONObject venue = (JSONObject) itemsObject.get("venue");

                    String name = (String) venue.get("name");
                    String Id = (String) venue.get("id");

                    //Parsing Address
                    JSONObject locationObject = (JSONObject) venue.get("location");
                    String adrArgument = (String) locationObject.get("address");
                    String adrArgument1 = (String) locationObject.get("postalCode");
                    String adrArgument2 = (String) locationObject.get("city");
                    String adrArgument3 = (String) locationObject.get("country");

                    String address = adrArgument + ", " + adrArgument1 + " " + adrArgument2 +
                            ", " + adrArgument3;

                    JSONArray categories = (JSONArray) venue.get("categories");
                    JSONObject categoriesObject = (JSONObject) categories.get(0);
                    String category = (String) categoriesObject.get("name");

                    places.add(new Place(Id, name, category, address));
                }

                return places;
            }
            return fall();
        }
    }

    private ArrayList<Place> fall(){
        ArrayList<Place> pl = new ArrayList<>();
        Place place = new Place("","", "","");
        pl.add(place);
        return pl;
    }

    private String urlBuilder(String city, double radius){
        return API_URL + CLIENT_ID  + "&client_secret=" + API_KEY + "&v=20180323&categoryId=4d4b7104d754a06370d81259,52e81612bcbc57f1066b7a21," +
                "52e81612bcbc57f1066b7a14,4bf58dd8d48988d163941735,4bf58dd8d48988d181941735,5744ccdfe4b0c0459246b4d9&limit=100&radius=" + radius +
                "&near=" + city;
    }

}
