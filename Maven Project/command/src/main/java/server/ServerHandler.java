package server;

import java.util.Collection;

public interface ServerHandler {
    Object getMovieCollection();
    Object getShowCollection();
    //Object getAllSeatsByShow(); //Discuss the implementation of the following 2 as well
    //void createBooking(Object show, Object customer, Collection<Object> rows);
}
