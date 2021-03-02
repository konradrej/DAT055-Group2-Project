package server;

import java.io.*;

import cinemaObjects.Movie;
import client.ReturnShowsByMovieCommand;

public class GetShowsByMovieCommand implements ServerCommand {

    private final Movie movie;

    public GetShowsByMovieCommand(Movie movie){
        this.movie = movie;
    }

    @Override
    public void execute(ServerHandler handler, ObjectOutputStream out) throws IOException {
        out.writeObject(new ReturnShowsByMovieCommand(
                handler.getShowsByMovie(movie)
        ));
    }
}