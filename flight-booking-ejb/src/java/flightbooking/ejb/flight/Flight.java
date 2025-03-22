/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package flightbooking.ejb.flight;

import flightbooking.entity.Flights;
import java.util.List;

/**
 *
 * @author vuquo
 */
public interface Flight {
    List<Flights> getAllFlight();
    
    Flights getFlightById(int flightId);
    
    void addFlight(Flights flight);
    
    void updateFlight(Flights flight);
    
    void deleteFlight(int flightId);
}
