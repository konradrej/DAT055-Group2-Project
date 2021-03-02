package server;

import cinemaObjects.*;
import collections.MovieCollection;
import collections.ShowCollection;
import misc.ResponseStatus;

import java.util.List;

/**
 * Contains functions used by ServerCommands and therefore need to be implemented
 * in the class being used by the server to handle incoming commands.
 *
 * @author Konrad Rej
 * @version 2021-03-02
 */
public interface ServerHandler {
    MovieCollection getMovieCollection();

    ShowCollection getShowCollection();

    ShowCollection getShowsByMovie(Movie movie);

    List<Booking> getBookingsBySSN(String SSN);

    Customer getCustomerBySSN(String SSN);

    ResponseStatus createBooking(Show show, Customer customer, List<Row> rows);

    void cancelBooking(Booking booking);
}
