package server;

import client.ReturnBookings;

import java.io.IOException;
import java.io.ObjectOutputStream;


public class GetBookingsBySSNCommand implements ServerCommand {
    private final String SSN;

    public GetBookingsBySSNCommand(String SSN) {
        this.SSN = SSN;
    }

    @Override
    public void execute(ServerHandler handler, ObjectOutputStream out) throws IOException {
        out.writeObject(new ReturnBookings(
                handler.getBookingsBySSN(this.SSN)
        ));
    }
}