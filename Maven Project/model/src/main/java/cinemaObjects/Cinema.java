package cinemaObjects;

import collections.CustomerCollection;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the cinema which has a name
 * and stores a collection of theatres.
 *
 * @author Erik Kieu
 * @version 2021-03-04
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


    @Override
    public boolean equals (Object o){
        if(this == o){
            return true;
        }
        if(o == null){
            return false;
        }
        if(getClass() != o.getClass()){
            return false;
        }

        Cinema cinema = (Cinema) o;
        return this.allTheaters.equals(cinema.getTheaterCollection()) &&
                this.name.equals(cinema.getCinemaName());
    }

    public Cinema readCinema() {
        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(this.name + ".txt"))) {

            Cinema readThis = (Cinema) stream.readObject();
            System.out.println("File: " + this.name + " has been read");
            return readThis;
        } catch (ClassCastException | ClassNotFoundException | NullPointerException | IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return new Cinema(this.name);
        }
    }

    public void serializeCinema(String s) {

        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(s))) {
            stream.writeObject(this);
        } catch (IOException e) {
            System.err.println("Error initializing stream " + e.getMessage());
            e.printStackTrace();
        }
    }
}
