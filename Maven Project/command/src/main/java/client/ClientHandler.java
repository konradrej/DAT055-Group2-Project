package client;

import java.util.*;

public interface ClientHandler {
    void setMovieCollection(Object movieCollection);
    void setShowCollection(Object showCollection);
    void setSeatsByShow(Collection<Object> seatsByShow);
    void setBookingsBySSN(Collection<Object> bookingsBySSN);
    void setBookingsByPhoneNumber(Collection<Object> bookingsBySSN);
    void setCustomerBySSN(Object customer);
}
