/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package flightbooking.ejb;

import flightbooking.entity.Flights;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public List<Flights> searchFlights(String departure, String arrival, Date date) {
        System.out.println("departure:");
        System.out.println(departure);
        System.out.println("arrival:");
        System.out.println(arrival);
        System.out.println("date:");
        System.out.println(date);
        TypedQuery<Flights> query = em.createQuery(
            "SELECT f FROM Flights f WHERE f.departureAirportId.name = :departure " +
            "AND f.arrivalAirportId.name = :arrival ", Flights.class); 
//        +
//            "AND CAST(f.departureTime AS DATE) = :date"

        query.setParameter("departure", departure);
        query.setParameter("arrival", arrival);

//        // Chỉ lấy phần ngày của tham số date để so sánh chính xác
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date formattedDate = null;
//        try {
//            formattedDate = sdf.parse(sdf.format(date));
//        } catch (ParseException ex) {
//            Logger.getLogger(FlightsFacade.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        query.setParameter("date", formattedDate);

        // Lấy danh sách kết quả
        List<Flights> flightsList = query.getResultList();
        
        System.out.println("Số lượng chuyến bay tìm thấy: " + flightsList.size());
        // In ra console GlassFish Server
        System.out.println("=== Search Results ===");
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
