package server;

import client.ReturnBookingsByPhoneNumberCommand;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;

public class GetBookingsByPhoneNumberCommand implements ServerCommand {

    private String phoneNumber;

    public GetBookingsByPhoneNumberCommand(String phoneNumber) { this.phoneNumber = phoneNumber; }

    @Override
    public void execute(ServerHandler handler, ObjectInputStream in, ObjectOutputStream out)
    throws IOException, ClassCastException {

        Collection<Object> bookingsBySSN = new ArrayList<>();

        try {
            bookingsBySSN = (Collection<Object>)handler.getBookingsBySSN(this.phoneNumber);
        } catch (ClassCastException e) {
            System.err.println("Class could not be casted. Message: " + e.getMessage());
            e.printStackTrace();
        } finally {
            out.writeObject(new ReturnBookingsByPhoneNumberCommand(
                    bookingsBySSN
            ));
        }

    }
}