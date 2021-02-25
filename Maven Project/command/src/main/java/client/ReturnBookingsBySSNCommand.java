package client;

import client.ClientCommand;
import client.ClientHandler;

import java.io.*;
import java.util.*;

public class ReturnBookingsBySSNCommand implements ClientCommand {

    private ArrayList<Object> bookingsBySSN;

    public ReturnBookingsBySSNCommand(ArrayList<Object> bookingsBySSN) { this.bookingsBySSN = bookingsBySSN; }

    @Override
    public void execute(ClientHandler handler, ObjectInputStream in, ObjectOutputStream out) throws IOException, ClassNotFoundException {
        handler.setBookings(bookingsBySSN);
    }
}