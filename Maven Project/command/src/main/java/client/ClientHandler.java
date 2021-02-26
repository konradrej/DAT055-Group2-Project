package client;

import misc.ResponseStatus;

import java.util.ArrayList;

public interface ClientHandler {
    void setMovieCollection(Object movieCollection);
    void setShowCollection(Object showCollection);
    void setBookings(ArrayList<Object> bookings);
    void setCustomerBySSN(Object customer);
    void setResponseStatus(ResponseStatus response);
}
