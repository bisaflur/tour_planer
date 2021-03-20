package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Place {
    private String name;
    private String address;
    private String id;
    private String category;

    public Place(String id, String name, String category, String address){
        this.id = id;
        this.name = name;
        this.category = category;
        this.address = address;
    }

    public String getName(){
        return name;
    }

    public String getAddress(){
        return address;
    }

    public String getId(){return id;}

    public String getCategory(){return category;}
}
