package flightbooking.ejb.booking;

import flightbooking.entity.Bookings;
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
public class BookingBean implements Booking {

    @PersistenceContext(unitName = "flightBookingPU")
    private EntityManager em;

    @Override
    public List<Bookings> getAllBookings() {
        TypedQuery<Bookings> query = em.createNamedQuery("Bookings.findAll", Bookings.class);
        return query.getResultList();
    }

    @Override
    public Bookings getBookingById(int bookingId) {
        return em.find(Bookings.class, bookingId);
    }

    @Override
    public void addBooking(Bookings booking) {
        em.persist(booking);
    }

    @Override
    public void updateBooking(Bookings booking) {
        em.merge(booking);
    }

    @Override
    public void deleteBooking(int bookingId) {
        Bookings booking = em.find(Bookings.class, bookingId);
        if (booking != null) {
            em.remove(booking);
        }
    }
}