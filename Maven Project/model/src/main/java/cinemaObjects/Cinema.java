package cinemaObjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the cinema which has a name
 * and stores a collection of theatres.
 *
 * @author Erik Kieu
 * @version 2021-03-02
 */
public class Cinema implements Serializable {

    private static final long serialVersionUID = -573847747221470914L;
    private String name;
    private List<Theater> allTheaters;

    /**
     * Constructor for initializing the cinemaObjects.Cinema instance
     *
     * @param name        the name of the cinema
     */
    public Cinema(String name) {
        this.name = name;
        this.allTheaters = new ArrayList<>();
    }

    /**
     * Method for getting a cinema
     *
     * @return returns the cinema instance itself
     */
    public Cinema getCinema() {
        return this;
    }

    /**
     * Method for getting cinema name
     *
     * @return returns the cinema name
     */
    public String getCinemaName() {
        return name;
    }

    /**
     * Method for getting theatres in collection of theatres.
     *
     * @return return
     */
    public List<Theater> getTheaterCollection() {
        return this.allTheaters;
    }

    /**
     * Method for creating a new theater given rows, cinema...
     */
    public void addTheater(Theater t) {
        this.allTheaters.add(t);
    }
}
