/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package flightbooking.ejb;

import flightbooking.entity.Passengers;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

/**
 *
 * @author vuquo
 */
@Stateless
public class PassengersFacade extends AbstractFacade<Passengers> implements PassengersFacadeLocal {

    @PersistenceContext(unitName = "flight-booking-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PassengersFacade() {
        super(Passengers.class);
    }

}
