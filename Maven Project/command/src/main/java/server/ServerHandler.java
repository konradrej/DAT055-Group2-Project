package server;

import java.util.Collection;

public interface ServerHandler {
    Object getMovieCollection();
    Object getShowCollection();
    Object getShowsByMovie(Object movie);
    Object getAllSeatsByShow(Object Show);
    Object getBookingsBySSN(String SSN);
    Object getCustomerBySSN(String SSN);
    String createBooking(Object show, Object customer, Collection<Object> rows);
    String createCustomer(String name, String phoneNumber, String SSN);
}
