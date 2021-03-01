package server;

import client.ReturnBookings;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class GetBookingsByPhoneNumberCommand implements ServerCommand {

    private final String phoneNumber;

    public GetBookingsByPhoneNumberCommand(String phoneNumber) { this.phoneNumber = phoneNumber; }

    @Override
    public void execute(ServerHandler handler, ObjectOutputStream out) throws IOException {
        out.writeObject(new ReturnBookings(
                handler.getBookingsBySSN(this.phoneNumber)
        ));
    }
}