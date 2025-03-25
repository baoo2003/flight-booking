/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package flightbooking.ejb;

import flightbooking.entity.Flights;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author vuquo
 */
@Local
public interface FlightsFacadeLocal {

    void create(Flights flights);

    void edit(Flights flights);

    void remove(Flights flights);

    Flights find(Object id);

    List<Flights> findAll();

    List<Flights> findRange(int[] range);

    int count();
    
    List<Flights> searchFlights(String departure, String arrival, Date date);
}
