package server;

import cinemaObjects.*;
import collections.MovieCollection;
import collections.ShowCollection;
import misc.ResponseStatus;

import java.util.List;

public interface ServerHandler {
    MovieCollection getMovieCollection();
    ShowCollection getShowCollection();
    ShowCollection getShowsByMovie(Movie movie);
    List<Booking> getBookingsBySSN(String SSN);
    Customer getCustomerBySSN(String SSN);
    ResponseStatus createBooking(Show show, Customer customer, List<Row> rows);
    String createCustomer(String name, String phoneNumber, String SSN);
    void cancelBooking(Booking booking);
}
