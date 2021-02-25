package client;

import java.io.*;
import java.util.ArrayList;

public class ReturnBookingsByPhoneNumberCommand implements ClientCommand {

    private ArrayList<Object> bookingsByPhoneNumber;

    public ReturnBookingsByPhoneNumberCommand(ArrayList<Object> bookingsByPhoneNumber) { this.bookingsByPhoneNumber = bookingsByPhoneNumber; }

    @Override
    public void execute(ClientHandler handler, ObjectInputStream in, ObjectOutputStream out) throws IOException, ClassNotFoundException {
        handler.setBookings(this.bookingsByPhoneNumber);
    }
}