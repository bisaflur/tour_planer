package tour.model;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "sights")
@XmlRootElement(name = "sight")
public class Sight {

    @JsonbTransient
    @XmlTransient
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, length = 256)
    private String name;

    @Column(nullable = false, length = 256)
    private String address;


    @Column(nullable = false, length = 256)
    private String city;

    private double radius;

    private Date dateOfRequest;

    public Sight(){
        super();
    }

    public Sight(String name, String address, String city, double radius){
        this.name = name;
        this.address = address;
        this.city = city;
        this.radius = radius;
        this.dateOfRequest = new Date();
    }


    @JsonbTransient
    @XmlTransient
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Date getDateOfRequest() {
        return dateOfRequest;
    }

    public void setDateOfRequest(Date dateOfRequest) {
        this.dateOfRequest = dateOfRequest;
    }

    @Override
    public String toString(){
        return String.format("Sight [name='%s', address='%s', city='%s', radius='%f', dateOfRequest='%s']",name,address,city,radius,dateOfRequest);
    }

    @Override
    public int hashCode(){
        return Objects.hash(name,address,city,radius,dateOfRequest);
    }

    @Override
    public boolean equals(final Object obj){

        if (this == obj)
            return true;
        if (!(obj instanceof Sight))
            return false;
        final Sight other = (Sight) obj;
        return Objects.equals(name,other.name) && Objects.equals(address,other.address) && Objects.equals(city,other.city) && Objects.equals(radius,other.radius) && Objects.equals(dateOfRequest,other.dateOfRequest);
    }
}
