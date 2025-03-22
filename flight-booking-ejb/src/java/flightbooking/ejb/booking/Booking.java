package flightbooking.ejb.booking;

import flightbooking.entity.Bookings;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author vuquo
 */
@Local
public interface Booking {

    List<Bookings> getAllBookings();

    Bookings getBookingById(int bookingId);

    void addBooking(Bookings booking);

    void updateBooking(Bookings booking);

    void deleteBooking(int bookingId);
}