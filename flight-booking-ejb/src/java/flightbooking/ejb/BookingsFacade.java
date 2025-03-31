/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package flightbooking.ejb;

import flightbooking.entity.Bookings;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author vuquo
 */
@Stateless
public class BookingsFacade extends AbstractFacade<Bookings> implements BookingsFacadeLocal {

    @PersistenceContext(unitName = "flight-booking-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BookingsFacade() {
        super(Bookings.class);
    }
    
    @Override
    public List<Bookings> findByUserId(int userId) {
        try {
            TypedQuery<Bookings> query = em.createQuery(
                    "SELECT b FROM Bookings b WHERE b.userId.userId = :userId",
                    Bookings.class
            );
            query.setParameter("userId", userId);
            
            List<Bookings> bookingList = query.getResultList();
            return bookingList;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
