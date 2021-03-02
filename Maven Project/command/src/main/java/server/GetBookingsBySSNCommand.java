package server;

import client.ReturnBookingsCommand;

import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * This class implements the ServerCommand interface,
 * and is used to get all bookings given a social security number,
 * as well as calling the ReturnBookingsCommand with
 * the social security number.
 *
 * @author Anthon Lenander
 * @version 2021-02-03
 */
public class GetBookingsBySSNCommand implements ServerCommand {
    private final String SSN;

    public GetBookingsBySSNCommand(String SSN) {
        this.SSN = SSN;
    }

    @Override
    public void execute(ServerHandler handler, ObjectOutputStream out) throws IOException {
        out.writeObject(new ReturnBookingsCommand(
                handler.getBookingsBySSN(this.SSN)
        ));
    }
}