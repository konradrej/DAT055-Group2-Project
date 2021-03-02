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
 * @version 2021-02-03
 */
public class GetBookingsByPhoneNumberCommand implements ServerCommand {

    private final String phoneNumber;

    public GetBookingsByPhoneNumberCommand(String phoneNumber) { this.phoneNumber = phoneNumber; }

    @Override
    public void execute(ServerHandler handler, ObjectOutputStream out) throws IOException {
        out.writeObject(new ReturnBookingsCommand(
                handler.getBookingsBySSN(this.phoneNumber)
        ));
    }
}