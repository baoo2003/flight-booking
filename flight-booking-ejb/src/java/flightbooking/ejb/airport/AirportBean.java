package flightbooking.ejb.airport;

import flightbooking.entity.Airports;
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
public class AirportBean implements Airport {

    @PersistenceContext(unitName = "flightBookingPU")
    private EntityManager em;

    @Override
    public List<Airports> getAllAirports() {
        TypedQuery<Airports> query = em.createNamedQuery("Airports.findAll", Airports.class);
        return query.getResultList();
    }

    @Override
    public Airports getAirportByCode(String code) {
        TypedQuery<Airports> query = em.createNamedQuery("Airports.findByCode", Airports.class);
        query.setParameter("code", code);
        List<Airports> results = query.getResultList();
        if (results.isEmpty()) {
            return null;
        }
        return results.get(0);
    }

    @Override
    public void addAirport(Airports airport) {
        em.persist(airport);
    }

    @Override
    public void updateAirport(Airports airport) {
        em.merge(airport);
    }

    @Override
    public void deleteAirport(int airportId) {
        Airports airport = em.find(Airports.class, airportId);
        if (airport != null) {
            em.remove(airport);
        }
    }
}