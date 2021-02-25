package client;

import java.util.*;

public interface ClientHandler {
    void setMovieCollection(Object movieCollection);
    void setShowCollection(Object showCollection);
    void setSeatsByShow(Collection<Object> seatsByShow);
    void setBookings(Collection<Object> bookings);
    void setCustomerBySSN(Object customer);
}
