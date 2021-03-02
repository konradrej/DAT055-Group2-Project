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

    public GetMoviesCommand(){}

    @Override
    public void execute(ServerHandler handler, ObjectOutputStream out) throws IOException {
        out.writeObject(new ReturnMoviesCommand(
                handler.getMovieCollection()
        ));
    }
}
