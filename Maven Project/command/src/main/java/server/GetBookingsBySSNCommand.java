package server;

import server.ServerCommand;
import server.ServerHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class GetBookingsBySSNCommand implements ServerCommand, Serializable {

    private String SSN;

    public GetBookingsBySSNCommand(String SSN) { this.SSN = SSN; }

    @Override
    public void execute(ServerHandler handler, ObjectInputStream in, ObjectOutputStream out) throws IOException {
        out.writeObject(
                handler.getBookingsBySSN(SSN)
        );
    }
}