package client;

import collections.ShowCollection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * This class implements the ClientCommand interface
 * and serves as a response to the ServerCommand
 * GetShowsByMovieCommand, returning a collection
 * of shows given a specific movie.
 *
 * @author Anthon Lenander
 * @version 2021-02-03
 */
public class ReturnShowsByMovieCommand implements ClientCommand {

    private final ShowCollection showCollection;

    public ReturnShowsByMovieCommand(ShowCollection showCollection) {
        this.showCollection = showCollection;
    }

    @Override
    public void execute(ClientHandler handler) throws IOException {
        handler.setShowCollection(showCollection);
    }
}
