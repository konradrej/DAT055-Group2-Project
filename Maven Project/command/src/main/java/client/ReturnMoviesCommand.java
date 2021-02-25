package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ReturnMoviesCommand implements ClientCommand {

    private Object movieCollection;

    public ReturnMoviesCommand(Object movieCollection){
        this.movieCollection = movieCollection;
    }

    @Override
    public void execute(ClientHandler handler, ObjectInputStream in, ObjectOutputStream out) throws IOException, ClassNotFoundException {
        handler.setMovieCollection(movieCollection);
    }
}