package server;

import client.ReturnMoviesCommand;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class GetMoviesCommand implements ServerCommand {

    public GetMoviesCommand(){}

    @Override
    public void execute(ServerHandler handler, ObjectInputStream in, ObjectOutputStream out) throws IOException {
        out.writeObject(new ReturnMoviesCommand(
                handler.getMovieCollection()
        ));
    }
}
