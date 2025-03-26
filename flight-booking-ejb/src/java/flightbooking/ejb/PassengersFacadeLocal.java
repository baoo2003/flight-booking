/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package flightbooking.ejb;

import flightbooking.entity.Passengers;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author vuquo
 */
@Local
public interface PassengersFacadeLocal {

    void create(Passengers passengers);

    void edit(Passengers passengers);

    void remove(Passengers passengers);

    Passengers find(Object id);

    List<Passengers> findAll();

    List<Passengers> findRange(int[] range);

    int count();    
}
