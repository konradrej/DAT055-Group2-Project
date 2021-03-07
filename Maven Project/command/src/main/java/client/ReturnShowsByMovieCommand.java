package client;

import collections.ShowCollection;

import java.io.IOException;


/**
 * This class implements the ClientCommand interface
 * and serves as a response to the ServerCommand
 * GetShowsByMovieCommand, returning a collection
 * of shows given a specific movie.
 *
 * @author Anthon Lenander
 * @version 2021-03-02
 */
public class ReturnShowsByMovieCommand implements ClientCommand {

    private final ShowCollection showCollection;

    /**
     * Constructor for setting the collection of shows to be returned
     *
     * @param showCollection the collection of shows to be returned
     */
    public ReturnShowsByMovieCommand(ShowCollection showCollection) {
        this.showCollection = showCollection;
    }

    /**
     * Method for setting the clients show collection
     *
     * @param handler A reference to the ClientHandler
     * @throws IOException If an input or output exception occurred
     */
    @Override
    public void execute(ClientHandler handler) throws IOException {
        handler.setShowCollection(showCollection);
    }
}