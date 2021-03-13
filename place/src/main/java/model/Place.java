package model;

public class Place {
    private String name;
    private String address;
    private double lat;
    private double lon;

    public Place(String name, String address, double lat, double lon){
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.address = address;
    }

    public String getName(){
        return name;
    }

    public String getAddress(){
        return address;
    }

    public double getLat(){
        return lat;
    }

    public double getLon(){
        return lon;
    }
}
