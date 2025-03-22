/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package flightbooking.ejb.airport;

import flightbooking.entity.Airports;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author vuquo
 */

@Local
public interface Airport {
    List<Airports> getAllAirports();

    Airports getAirportByCode(String code);

    void addAirport(Airports airport);

    void updateAirport(Airports airport);

    void deleteAirport(int airportId);
}
