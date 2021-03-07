package cinemaObjects;


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
    private final String name;
    private final List<Theater> allTheaters;

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
     * Method for getting cinema name
     *
     * @return returns the cinema name
     */
    public String getCinemaName() {
        return name;
    }

    /**
     * Method for getting theatres in List of theatres.
     *
     * @return return
     */
    public List<Theater> getAllTheaters() {
        return this.allTheaters;
    }

    /**
     * Method for creating a new theater
     *
     * @param t     the new theater
     */
    public void addTheater(Theater t) {
        this.allTheaters.add(t);
    }

    /**
     * Method for reading a cinema
     */

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

    /**
     * Method for serializing a cinema
     */
    public void serializeCinema(String s) {

        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(s))) {
            stream.writeObject(this);
        } catch (IOException e) {
            System.err.println("Error initializing stream " + e.getMessage());
            e.printStackTrace();
        }
    }
}
