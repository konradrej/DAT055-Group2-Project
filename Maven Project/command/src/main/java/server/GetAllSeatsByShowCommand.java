package server;

import server.ServerCommand;
import server.ServerHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class GetAllSeatsByShowCommand implements ServerCommand, Serializable {

    private Object show;

    public GetAllSeatsByShowCommand(Object show) { this.show = show; }

    @Override
    public void execute(ServerHandler handler, ObjectInputStream in, ObjectOutputStream out) throws IOException {
        out.writeObject(
                handler.getAllSeatsByShow(show)
        );
    }
}
