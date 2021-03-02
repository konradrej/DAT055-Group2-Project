package server;

import java.io.*;

import cinemaObjects.CinemaDate;
import cinemaObjects.Movie;
import client.ReturnShowsByMovieCommand;

/**
 * This class implements the ServerCommand interface,
 * and is used to get all Shows  given a movie,
 * as well as calling the ReturnShowsByMovieCommand
 * with the getShowsByMovie method from the ServerHandler
 * as input parameter.
 *
 * @author Anthon Lenander
 * @version 2021-03-02
 */
public class GetShowsByMovieDateRangeCommand implements ServerCommand {

    private final Movie movie;
    private final CinemaDate startDate;
    private final CinemaDate endDate;

    /**
     * Constructor for initializing the movie of this instance
     *
     * @param movie     the movie to get shows by
     * @param startDate first showing earliest date
     * @param endDate   last showing latest date
     */
    public GetShowsByMovieDateRangeCommand(Movie movie, CinemaDate startDate, CinemaDate endDate) {
        this.movie = movie;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Method for getting a collection of shows given a movie
     *
     * @param handler A reference to the ServerHandler
     * @param out     A reference to the ObjectOutputStream
     * @throws IOException If an input or output exception occurred
     */
    @Override
    public void execute(ServerHandler handler, ObjectOutputStream out) throws IOException {
        out.writeObject(new ReturnShowsByMovieCommand(
                handler.getShowsByMovieDateRange(movie, startDate, endDate)
        ));
    }
}