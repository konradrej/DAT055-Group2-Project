package server;

import java.io.*;
import client.ReturnShowsByMovieCommand;

public class GetShowsByMovieCommand implements ServerCommand {

    private Object movie;

    public GetShowsByMovieCommand(Object movie){
        this.movie = movie;
    }

    @Override
    public void execute(ServerHandler handler, ObjectInputStream in, ObjectOutputStream out) throws IOException {
        out.writeObject(new ReturnShowsByMovieCommand(
                handler.getShowsByMovie(movie)
        ));
    }
}