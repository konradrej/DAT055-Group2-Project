package server;

import client.ReturnMoviesCommand;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * This class implements the ServerCommand interface,
 * and is used to get all movies, as well as calling
 * the ReturnMoviesCommand with the getMovieCollection
 * method call from the ServerHandler.
 *
 * @author Anthon Lenander
 * @version 2021-02-03
 */
public class GetMoviesCommand implements ServerCommand {

    /**
     * Empty constructor
     */
    public GetMoviesCommand() {
    }

    /**
     * Method for getting a customer given a social security number if there is any
     *
     * @param handler A reference to the ServerHandler
     * @param out     A reference to the ObjectOutputStream
     * @throws IOException
     */
    @Override
    public void execute(ServerHandler handler, ObjectOutputStream out) throws IOException {
        out.writeObject(new ReturnMoviesCommand(
                handler.getMovieCollection()
        ));
    }
}