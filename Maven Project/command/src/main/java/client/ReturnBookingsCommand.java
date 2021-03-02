package client;

import cinemaObjects.Booking;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the ClientCommand interface
 * and serves as a response to the ServerCommand
 * GetBookingsByPhoneNumberCommand and GetBookingsBySSNCommand,
 * returning a list of bookings.
 *
 * @author Anthon Lenander
 * @version 2021-02-03
 */
public class ReturnBookingsCommand implements ClientCommand {

    private final List<Booking> bookings;

    public ReturnBookingsCommand(List<Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public void execute(ClientHandler handler) throws IOException {
        handler.setBookings(this.bookings);
    }
}