package client;

import client.ClientCommand;
import client.ClientHandler;
import java.io.*;
import java.util.*;

public class ReturnBookingsByPhoneNumberCommand implements ClientCommand {

    private Collection<Object> bookingsByPhoneNumber;

    public ReturnBookingsByPhoneNumberCommand(Collection<Object> bookingsByPhoneNumber) { this.bookingsByPhoneNumber = bookingsByPhoneNumber; }

    @Override
    public void execute(ClientHandler handler, ObjectInputStream in, ObjectOutputStream out) throws IOException, ClassNotFoundException {
        handler.setBookings(this.bookingsByPhoneNumber);
    }
}