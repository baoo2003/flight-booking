/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package flightbooking.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vuquo
 */
@Entity
@Table(name = "passengers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Passengers.findAll", query = "SELECT p FROM Passengers p"),
    @NamedQuery(name = "Passengers.findByPassengerId", query = "SELECT p FROM Passengers p WHERE p.passengerId = :passengerId"),
    @NamedQuery(name = "Passengers.findByFirstName", query = "SELECT p FROM Passengers p WHERE p.firstName = :firstName"),
    @NamedQuery(name = "Passengers.findByLastName", query = "SELECT p FROM Passengers p WHERE p.lastName = :lastName"),
    @NamedQuery(name = "Passengers.findByDateOfBirth", query = "SELECT p FROM Passengers p WHERE p.dateOfBirth = :dateOfBirth"),
    @NamedQuery(name = "Passengers.findByGender", query = "SELECT p FROM Passengers p WHERE p.gender = :gender"),
    @NamedQuery(name = "Passengers.findByPassportNumber", query = "SELECT p FROM Passengers p WHERE p.passportNumber = :passportNumber"),
    @NamedQuery(name = "Passengers.findByType", query = "SELECT p FROM Passengers p WHERE p.type = :type"),
    @NamedQuery(name = "Passengers.findByIdCardNumber", query = "SELECT p FROM Passengers p WHERE p.idCardNumber = :idCardNumber")})
public class Passengers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "passenger_id")
    private Integer passengerId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "first_name")
    private String firstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Size(max = 10)
    @Column(name = "gender")
    private String gender;
    @Size(max = 50)
    @Column(name = "passport_number")
    private String passportNumber;
    @Basic(optional = false)
    @NotNull    
    @Size(min = 1, max = 50)    
    @Column(name = "type")
    private String type;
    @Column(name = "id_card_number")    
    private String idCardNumber;
    @JoinColumn(name = "booking_id", referencedColumnName = "booking_id")
    @ManyToOne(optional = false)
    private Bookings bookingId;

    public Passengers() {
    }

    public Passengers(Integer passengerId) {
        this.passengerId = passengerId;
    }

    public Passengers(Integer passengerId, String firstName, String lastName, String type) {
        this.passengerId = passengerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
    }

    public Integer getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Integer passengerId) {
        this.passengerId = passengerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public Bookings getBookingId() {
        return bookingId;
    }

    public void setBookingId(Bookings bookingId) {
        this.bookingId = bookingId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (passengerId != null ? passengerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Passengers)) {
            return false;
        }
        Passengers other = (Passengers) object;
        if ((this.passengerId == null && other.passengerId != null) || (this.passengerId != null && !this.passengerId.equals(other.passengerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "flightbooking.entity.Passengers[ passengerId=" + passengerId + " ]";
    }
    
}
