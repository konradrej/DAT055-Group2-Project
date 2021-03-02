package client;

import collections.MovieCollection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ReturnMoviesCommand implements ClientCommand {

    private final MovieCollection movieCollection;

    public ReturnMoviesCommand(MovieCollection movieCollection){
        this.movieCollection = movieCollection;
    }

    @Override
    public void execute(ClientHandler handler) throws IOException {
        handler.setMovieCollection(movieCollection);
    }
}