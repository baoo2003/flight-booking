/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package flightbooking.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vuquo
 */
@Entity
@Table(name = "airports")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Airports.findAll", query = "SELECT a FROM Airports a"),
    @NamedQuery(name = "Airports.findByAirportId", query = "SELECT a FROM Airports a WHERE a.airportId = :airportId"),
    @NamedQuery(name = "Airports.findByCode", query = "SELECT a FROM Airports a WHERE a.code = :code"),
    @NamedQuery(name = "Airports.findByName", query = "SELECT a FROM Airports a WHERE a.name = :name"),
    @NamedQuery(name = "Airports.findByCity", query = "SELECT a FROM Airports a WHERE a.city = :city"),
    @NamedQuery(name = "Airports.findByCountry", query = "SELECT a FROM Airports a WHERE a.country = :country")})
public class Airports implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "airport_id")
    private Integer airportId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "code")
    private String code;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;
    @Size(max = 100)
    @Column(name = "city")
    private String city;
    @Size(max = 100)
    @Column(name = "country")
    private String country;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "arrivalAirportId")
    private Collection<Flights> flightsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "departureAirportId")
    private Collection<Flights> flightsCollection1;

    public Airports() {
    }

    public Airports(Integer airportId) {
        this.airportId = airportId;
    }

    public Airports(Integer airportId, String code, String name) {
        this.airportId = airportId;
        this.code = code;
        this.name = name;
    }

    public Integer getAirportId() {
        return airportId;
    }

    public void setAirportId(Integer airportId) {
        this.airportId = airportId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @XmlTransient
    public Collection<Flights> getFlightsCollection() {
        return flightsCollection;
    }

    public void setFlightsCollection(Collection<Flights> flightsCollection) {
        this.flightsCollection = flightsCollection;
    }

    @XmlTransient
    public Collection<Flights> getFlightsCollection1() {
        return flightsCollection1;
    }

    public void setFlightsCollection1(Collection<Flights> flightsCollection1) {
        this.flightsCollection1 = flightsCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (airportId != null ? airportId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Airports)) {
            return false;
        }
        Airports other = (Airports) object;
        if ((this.airportId == null && other.airportId != null) || (this.airportId != null && !this.airportId.equals(other.airportId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "flightbooking.entity.Airports[ airportId=" + airportId + " ]";
    }
    
}
