package client;

import cinemaObjects.Booking;
import cinemaObjects.Customer;
import collections.MovieCollection;
import collections.ShowCollection;
import misc.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

public interface ClientHandler {
    void setMovieCollection(MovieCollection movieCollection);
    void setShowCollection(ShowCollection showCollection);
    void setBookings(List<Booking> bookings);
    void setCustomerBySSN(Customer customer);
    void setResponseStatus(ResponseStatus response);
}
