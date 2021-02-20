package server;

import client.ReturnShowsByMovieCommand;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class GetShowByMovieCommand implements ServerCommand, Serializable {
    private Object movie;

    public GetShowByMovieCommand(Object movie){
        this.movie = movie;
    }

    @Override
    public void execute(ServerHandler handler, ObjectInputStream in, ObjectOutputStream out) throws IOException {
        out.writeObject(new ReturnShowsByMovieCommand(
                handler.getShowCollection()
        ));
    }
}