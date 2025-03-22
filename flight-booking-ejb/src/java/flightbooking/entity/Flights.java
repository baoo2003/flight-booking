/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package flightbooking.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vuquo
 */
@Entity
@Table(name = "flights")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Flights.findAll", query = "SELECT f FROM Flights f"),
    @NamedQuery(name = "Flights.findByFlightId", query = "SELECT f FROM Flights f WHERE f.flightId = :flightId"),
    @NamedQuery(name = "Flights.findByFlightNumber", query = "SELECT f FROM Flights f WHERE f.flightNumber = :flightNumber"),
    @NamedQuery(name = "Flights.findByDepartureTime", query = "SELECT f FROM Flights f WHERE f.departureTime = :departureTime"),
    @NamedQuery(name = "Flights.findByArrivalTime", query = "SELECT f FROM Flights f WHERE f.arrivalTime = :arrivalTime"),
    @NamedQuery(name = "Flights.findByDuration", query = "SELECT f FROM Flights f WHERE f.duration = :duration"),
    @NamedQuery(name = "Flights.findByPrice", query = "SELECT f FROM Flights f WHERE f.price = :price"),
    @NamedQuery(name = "Flights.findByAvailableSeats", query = "SELECT f FROM Flights f WHERE f.availableSeats = :availableSeats"),
    @NamedQuery(name = "Flights.findByMaxSeats", query = "SELECT f FROM Flights f WHERE f.maxSeats = :maxSeats")})
public class Flights implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "flight_id")
    private Integer flightId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "flight_number")
    private String flightNumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "departure_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date departureTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "arrival_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date arrivalTime;
    @Column(name = "duration")
    private Integer duration;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private BigDecimal price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "available_seats")
    private int availableSeats;
    @Basic(optional = false)
    @NotNull
    @Column(name = "max_seats")
    private int maxSeats;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "flightId")
    private Collection<Bookings> bookingsCollection;
    @JoinColumn(name = "arrival_airport_id", referencedColumnName = "airport_id")
    @ManyToOne(optional = false)
    private Airports arrivalAirportId;
    @JoinColumn(name = "departure_airport_id", referencedColumnName = "airport_id")
    @ManyToOne(optional = false)
    private Airports departureAirportId;

    public Flights() {
    }

    public Flights(Integer flightId) {
        this.flightId = flightId;
    }

    public Flights(Integer flightId, String flightNumber, Date departureTime, Date arrivalTime, BigDecimal price, int availableSeats, int maxSeats) {
        this.flightId = flightId;
        this.flightNumber = flightNumber;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
        this.availableSeats = availableSeats;
        this.maxSeats = maxSeats;
    }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public int getMaxSeats() {
        return maxSeats;
    }

    public void setMaxSeats(int maxSeats) {
        this.maxSeats = maxSeats;
    }

    @XmlTransient
    public Collection<Bookings> getBookingsCollection() {
        return bookingsCollection;
    }

    public void setBookingsCollection(Collection<Bookings> bookingsCollection) {
        this.bookingsCollection = bookingsCollection;
    }

    public Airports getArrivalAirportId() {
        return arrivalAirportId;
    }

    public void setArrivalAirportId(Airports arrivalAirportId) {
        this.arrivalAirportId = arrivalAirportId;
    }

    public Airports getDepartureAirportId() {
        return departureAirportId;
    }

    public void setDepartureAirportId(Airports departureAirportId) {
        this.departureAirportId = departureAirportId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (flightId != null ? flightId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Flights)) {
            return false;
        }
        Flights other = (Flights) object;
        if ((this.flightId == null && other.flightId != null) || (this.flightId != null && !this.flightId.equals(other.flightId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "flightbooking.entity.Flights[ flightId=" + flightId + " ]";
    }
    
}
