package server;

import client.ReturnBookingsBySSNCommand;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

public class GetBookingsBySSNCommand implements ServerCommand {

    private String SSN;

    public GetBookingsBySSNCommand(String SSN) { this.SSN = SSN; }

    @Override
    public void execute(ServerHandler handler, ObjectInputStream in, ObjectOutputStream out) throws IOException {

        Collection<Object> bookingsBySSN = Collections.emptyList();

        try {
            bookingsBySSN = (Collection<Object>)handler.getBookingsBySSN(this.SSN);
        } catch (ClassCastException e) {
            System.err.println("Class could not be casted. Message: " + e.getMessage());
            e.printStackTrace();
        } finally {
            out.writeObject(new ReturnBookingsBySSNCommand(
                    bookingsBySSN
            ));
        }

    }
}