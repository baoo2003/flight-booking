/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package flightbooking.ejb.passenger;

import flightbooking.entity.Passengers;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author vuquo
 */
public class PassengerBean implements Passenger {
    
    @PersistenceContext(unitName = "flightBookingPU")
    private EntityManager em;
    
    @Override
    public List<Passengers> getAllPassenger() {
        TypedQuery<Passengers> query = em.createNamedQuery("Passengers.findAll", Passengers.class);
        return query.getResultList();
    }

    @Override
    public Passengers getPassengerById(int passengerId) {
        return em.find(Passengers.class, passengerId);
    }

    @Override
    public void addPassenger(Passengers passenger) {
        em.persist(passenger);
    }

    @Override
    public void updatePassenger(Passengers passenger) {
        em.merge(passenger);
    }

    @Override
    public void deletePassenger(int passengerId) {
        Passengers passenger = em.find(Passengers.class, passengerId);
        if(passenger != null) {
            em.remove(passenger);
        }
    }
    
}
