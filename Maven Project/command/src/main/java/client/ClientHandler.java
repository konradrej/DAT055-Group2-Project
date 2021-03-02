package client;

import cinemaObjects.Booking;
import cinemaObjects.Customer;
import collections.MovieCollection;
import collections.ShowCollection;
import misc.ResponseStatus;

import java.util.List;

/**
 * Contains functions used by ClientCommands and therefore need to be implemented
 * in the class being used by the client to handle incoming commands.
 *
 * @author Konrad Rej
 * @version 2021-03-02
 */
public interface ClientHandler {
    void setMovieCollection(MovieCollection movieCollection);

    void setShowCollection(ShowCollection showCollection);

    void setBookings(List<Booking> bookings);

    void setCustomerBySSN(Customer customer);

    void setResponseStatus(ResponseStatus response);
}
