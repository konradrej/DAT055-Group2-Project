package server;

import client.ResponseStatus;

import java.util.ArrayList;

public interface ServerHandler {
    Object getMovieCollection();
    Object getShowCollection();
    Object getShowsByMovie(Object movie);
    Object getBookingsBySSN(String SSN);
    Object getCustomerBySSN(String SSN);
    ResponseStatus createBooking(Object show, Object customer, ArrayList<Object> rows);
    String createCustomer(String name, String phoneNumber, String SSN);
}
