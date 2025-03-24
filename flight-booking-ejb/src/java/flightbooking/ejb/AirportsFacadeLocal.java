/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package flightbooking.ejb;

import flightbooking.entity.Airports;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author vuquo
 */
@Local
public interface AirportsFacadeLocal {

    void create(Airports airports);

    void edit(Airports airports);

    void remove(Airports airports);

    Airports find(Object id);

    List<Airports> findAll();

    List<Airports> findRange(int[] range);

    int count();
    
}
