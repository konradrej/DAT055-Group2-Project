package server;

import client.ReturnBookingsCommand;

import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * This class implements the ServerCommand interface,
 * and is used to get all bookings given a phone number,
 * as well as calling the ReturnBookingsCommand with
 * the given phone number.
 *
 * @author Anthon Lenander
 * @version 2021-03-02
 */
public class GetBookingsByPhoneNumberCommand implements ServerCommand {

    private final String phoneNumber;

    /**
     * Constructor for initializing the phone number of this instance
     *
     * @param phoneNumber the phone number to set this instance's variable to
     */
    public GetBookingsByPhoneNumberCommand(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Method for returning all bookings given a phone number
     *
     * @param handler A reference to the ServerHandler
     * @param out     A reference to the ObjectOutputStream
     * @throws IOException If an input or output exception occurred
     */
    @Override
    public void execute(ServerHandler handler, ObjectOutputStream out) throws IOException {
        out.writeObject(new ReturnBookingsCommand(
                handler.getBookingsBySSN(this.phoneNumber)
        ));
    }
}