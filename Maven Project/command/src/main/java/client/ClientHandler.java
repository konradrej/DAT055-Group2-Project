package client;

import java.util.ArrayList;

public interface ClientHandler {
    void setMovieCollection(Object movieCollection);
    void setShowCollection(Object showCollection);
    void setSeatsByShow(ArrayList<Object> seatsByShow);
    void setBookings(ArrayList<Object> bookings);
    void setCustomerBySSN(Object customer);
}
