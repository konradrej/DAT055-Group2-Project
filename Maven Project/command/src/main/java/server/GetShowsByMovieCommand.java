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

    public GetShowsByMovieCommand(Movie movie) {
        this.movie = movie;
    }

    @Override
    public void execute(ServerHandler handler, ObjectOutputStream out) throws IOException {
        out.writeObject(new ReturnShowsByMovieCommand(
                handler.getShowsByMovie(movie)
        ));
    }
}