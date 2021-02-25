package cinemaObjects;

import java.io.Serializable;
import java.util.*;

/**
 * @author Anthon Lenander, Erik Kieu, Phong Nguyen
 * @version version 0.0.0
 */
public class Cinema implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -573847747221470914L;
	private String name;
    private ArrayList <Theater> allTheaters;


    /**
     * Constructor for initializing the cinemaObjects.Cinema instance
     *
     * @param name              the name of the cinema
     * @param allTheaters       the Collection of allTheatres
     */
    public Cinema(String name, ArrayList <Theater> allTheaters){
        this.name = name;
        this.allTheaters = allTheaters;
    }


    /**
     * Method for setting a cinema name
     */
    public void setName(String name) {
        this.name = name;
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
     * @return      returns the cinema name
     */
    public String getCinemaName() {
        return name;
    }


    /**
     * Method for getting theatres in collection of theatres.
     *
     * @return return
     */
    public ArrayList <Theater> getTheaterCollection() {
        return this.allTheaters;
    }

    /**
     * Method for creating a new theater given rows, cinema...
     *
     */
    public void addTheater() {
        // DO WE NEED THESE?
        //TODO: parameters
        //TODO: CODE
    }
}
