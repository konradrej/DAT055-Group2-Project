package client;

import collections.MovieCollection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * This class implements the ClientCommand interface
 * and serves as a response to the ServerCommand
 * CreateBookingCommand, returning an instance of
 * collection of movies.
 *
 * @author Anthon Lenander
 * @version 2021-02-03
 */
public class ReturnMoviesCommand implements ClientCommand {

    private final MovieCollection movieCollection;

    /**
     * Constructor for initializing the collection of movies to be returned
     *
     * @param movieCollection the colleciton of movies to be returned
     */
    public ReturnMoviesCommand(MovieCollection movieCollection) {
        this.movieCollection = movieCollection;
    }

    /**
     * Method for setting the clients collection of movies
     *
     * @param handler A reference to the ClientHandler
     * @throws IOException
     */
    @Override
    public void execute(ClientHandler handler) throws IOException {
        handler.setMovieCollection(movieCollection);
    }
}