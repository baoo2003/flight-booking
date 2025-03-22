/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package flightbooking.ejb.flight;

import flightbooking.entity.Flights;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author vuquo
 */
public class FlightBean implements Flight {
    
    @PersistenceContext(unitName = "flightBookingPU")
    private EntityManager em;
    
    @Override
    public List<Flights> getAllFlight() {
        TypedQuery<Flights> query = em.createNamedQuery("Flights.findAll", Flights.class);
        return query.getResultList();
    }

    @Override
    public Flights getFlightById(int flightId) {
        return em.find(Flights.class, flightId);
    }

    @Override
    public void addFlight(Flights flight) {
        em.persist(flight);
    }

    @Override
    public void updateFlight(Flights flight) {
        em.merge(flight);
    }

    @Override
    public void deleteFlight(int flightId) {
        Flights flight = em.find(Flights.class, flightId);
        if(flight != null){
            em.remove(flight);
        }
    }
}
