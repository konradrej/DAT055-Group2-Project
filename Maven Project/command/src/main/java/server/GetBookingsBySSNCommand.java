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
 * @version 2021-03-02
 */
public class GetBookingsBySSNCommand implements ServerCommand {
    private final String SSN;

    /**
     * Constructor for initializing the social security number of this instance
     *
     * @param SSN the social security number
     */
    public GetBookingsBySSNCommand(String SSN) {
        this.SSN = SSN;
    }

    /**
     * Method for returning all bookings given a social security number
     *
     * @param handler A reference to the ServerHandler
     * @param out     A reference to the ObjectOutputStream
     * @throws IOException If an input or output exception occurred
     */
    @Override
    public void execute(ServerHandler handler, ObjectOutputStream out) throws IOException {
        out.writeObject(new ReturnBookingsCommand(
                handler.getBookingsBySSN(this.SSN)
        ));
    }
}