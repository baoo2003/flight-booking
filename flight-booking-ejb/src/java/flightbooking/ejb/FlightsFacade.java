/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package flightbooking.ejb;

import flightbooking.entity.Flights;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author vuquo
 */
@Stateless
public class FlightsFacade extends AbstractFacade<Flights> implements FlightsFacadeLocal {

    @PersistenceContext(unitName = "flight-booking-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FlightsFacade() {
        super(Flights.class);
    }

    @Override
    public List<Flights> searchFlights(String departure, String arrival, String date) {
        System.out.println("departure: " + departure);
        System.out.println("arrival: " + arrival);
        System.out.println("date: " + date);

        // Convert String to java.sql.Date
        java.sql.Date sqlDate = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = sdf.parse(date);
            sqlDate = new java.sql.Date(utilDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return new ArrayList<>();  // Return empty list if date parsing fails
        }

        TypedQuery<Flights> query = em.createQuery(
            "SELECT f FROM Flights f WHERE f.departureAirportId.code = :departure " +
            "AND f.arrivalAirportId.code = :arrival " +
            "AND CAST(f.departureTime AS DATE) = :date", Flights.class
        );

        query.setParameter("departure", departure);
        query.setParameter("arrival", arrival);
        query.setParameter("date", sqlDate);  // Set parameter as java.sql.Date

        // Fetch results
        List<Flights> flightsList = query.getResultList();

        System.out.println("Số lượng chuyến bay tìm thấy: " + flightsList.size());
        for (Flights flight : flightsList) {
            System.out.println("Flight ID: " + flight.getFlightId() +
                               ", Number: " + flight.getFlightNumber() +
                               ", From: " + flight.getDepartureAirportId().getName() +
                               " -> To: " + flight.getArrivalAirportId().getName() +
                               ", Departure: " + flight.getDepartureTime() +
                               ", Arrival: " + flight.getArrivalTime() +
                               ", Price: " + flight.getPrice() +
                               ", Available Seats: " + flight.getAvailableSeats());
        }

        return flightsList;
    }
    
}
