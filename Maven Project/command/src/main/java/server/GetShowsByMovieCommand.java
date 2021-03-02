package server;

import java.io.*;

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
 * @version 2021-02-03
 */
public class GetShowsByMovieCommand implements ServerCommand {

    private final Movie movie;

    /**
     * Constructor for initializing the movie of this instance
     *
     * @param movie the movie to get shows by
     */
    public GetShowsByMovieCommand(Movie movie) {
        this.movie = movie;
    }

    /**
     * Method for getting a collection of shows given a movie
     *
     * @param handler A reference to the ServerHandler
     * @param out     A reference to the ObjectOutputStream
     * @throws IOException
     */
    @Override
    public void execute(ServerHandler handler, ObjectOutputStream out) throws IOException {
        out.writeObject(new ReturnShowsByMovieCommand(
                handler.getShowsByMovie(movie)
        ));
    }
}