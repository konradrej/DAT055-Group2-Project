package client;

import java.util.*;

public interface ClientHandler {
    void setMovieCollection(Object movieCollection);
    void setShowCollection(Object showCollection);
    //Collection<Object> getAllSeatsByShow(Object show); Discuss how to implement return methods
}
