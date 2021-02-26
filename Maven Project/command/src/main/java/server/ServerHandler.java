package server;

import misc.ResponseStatus;

import java.util.List;

public interface ServerHandler {
    Object getMovieCollection();
    Object getShowCollection();
    Object getShowsByMovie(Object movie);
    Object getBookingsBySSN(String SSN);
    Object getCustomerBySSN(String SSN);
    ResponseStatus createBooking(Object show, Object customer, List<Object> rows);
    String createCustomer(String name, String phoneNumber, String SSN);
    void cancelBooking(Object booking);
}
