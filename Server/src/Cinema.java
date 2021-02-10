import java.util.*;

import src.Theatre;

/**
 * @author Anthon Lenander, Erik Kieu, Phong Nguyen
 * @version version 0.0.0
 */
public class Cinema {

    private String name;
    private Collection<theatre> allTheatres;


    /**
     * Constructor for initializing the Cinema instance
     *
     * @param name      the name of the cinema
     */
    public Cinema(String name,....){
        //Todo: Cinema parameter
    }


    /**
     * Method for setting a cinema name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method for getting cinema name
     *
     * @return returns the cinema instance itself
     */
    public Cinema getCinema() {
        return this;
    }


    /**
     * Method for getting theatres in collection of theatres.
     *
     * @return return
     */
    public Collection <Theatre> getTheatreCollection() {
        //Todo: the code
        //TODO: return information is missing
    }

    /**
     * Creates new theatre given rows, cinema, ...
     * Adds the created theatre to the collection of theatres
     */
    public void addTheater(int theaterNumber,...) {
        //Todo: parameters for the method
        Theatre t = new theatre(c, r);
        this.allTheatres.add(t);
    }


}
