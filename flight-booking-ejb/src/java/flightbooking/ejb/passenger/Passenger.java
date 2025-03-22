/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package flightbooking.ejb.passenger;

import flightbooking.entity.Passengers;
import java.util.List;

/**
 *
 * @author vuquo
 */
public interface Passenger {
    List<Passengers> getAllPassenger();
    
    Passengers getPassengerById(int passengerId);
    
    void addPassenger(Passengers passenger);
    
    void updatePassenger(Passengers passenger);
    
    void deletePassenger(int passengerId);
}
