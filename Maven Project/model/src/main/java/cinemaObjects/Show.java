package cinemaObjects;

import java.io.Serializable;
import java.util.*;

/**
 * This class handles a show and contains the movie,
 * the date for the movie, the cinema where the movie
 * is viewed and the theater where the movie is viewed.
 *
 * @author Erik Kieu
 * @version 2021-03-04
 */
public class Show implements Serializable {

    private final String uniqueID;
    private final Movie movie;
    private final CinemaDate dayAndTime;
    private final Cinema cinema;
    private final Theater theater;

    /**
     * Constructor for initializing the show instance with corresponding parameter values
     *
     * @param movie      the movie that the show will play
     * @param dayAndTime the day and time of the show
     * @param cinema     what cinema the show will be played at
     * @param theater    what theater the show will be played at
     */
    public Show(Movie movie, CinemaDate dayAndTime, Cinema cinema, Theater theater) {
        this.movie = movie;
        this.dayAndTime = dayAndTime;
        this.cinema = cinema;
        this.theater = theater;
        this.uniqueID = UUID.randomUUID().toString();
    }

    /**
     * Constructor for initializing the show instance with corresponding parameter values
     *
     * @param movie      the movie that the show will play
     * @param dayAndTime the day and time of the show
     * @param cinema     what cinema the show will be played at
     * @param theater    what theater the show will be played at
     * @param uid        what unique ID the show will identify as
     */

    public Show(Movie movie, CinemaDate dayAndTime, Cinema cinema, Theater theater, String uid) {
        this.movie = movie;
        this.dayAndTime = dayAndTime;
        this.cinema = cinema;
        this.theater = theater;
        this.uniqueID = uid;
    }

    /**
     * Method for getting the unique ID of the show itself
     *
     * @return returns the unique ID assigned to this specific show
     */
    public String getUniqueID() {
        return this.uniqueID;
    }

    /**
     * Method for getting the movie that will be played
     *
     * @return returns the movie instance variable of this object
     */
    public Movie getMovie() {
        return this.movie;
    }

    /**
     * Method for getting the day and time at which the show will take place
     *
     * @return returns the day_and_time instance variable of this object
     */
    public CinemaDate getShowDateAndTime() {
        return this.dayAndTime;
    }

    /**
     * Method for getting the cinema at which the show will be played
     *
     * @return returns the cinema instance variable of this object
     */
    public Cinema getCinema() {
        return this.cinema;
    }

    /**
     * Method for getting the theater at which the show will be played
     *
     * @return returns the theater instance variable of this object
     */
    public Theater getTheater() {
        return this.theater;
    }

    /**
     * Method for finding all available seats in the theater that the show will be held in
     *
     * @return returns a list of Seat, containing all available seats
     */
    public ArrayList<Seat> getAllAvailableSeats() {
        ArrayList<Seat> allAvailableSeats = new ArrayList<>();

        for (Row r : this.theater.getAllRows()) {
            allAvailableSeats.addAll(r.getAllAvailableSeats());
        }
        return allAvailableSeats;
    }

    /**
     * Method to get the Movie , Cinema, Theater and time of the show
     *
     * @return a string representing the show
     */
    public String toString() {
        return "Movie: " + this.movie.getTitle() + "\nDate and time: " + this.dayAndTime.toString() +
                "\nCinema: " + this.cinema.getCinemaName() + "\nTheater Number: " + theater.getTheaterNumber();
    }
}