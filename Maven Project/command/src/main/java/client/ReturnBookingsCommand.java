package client;

import cinemaObjects.Booking;

import java.io.*;
import java.util.List;

/**
 * This class implements the ClientCommand interface
 * and serves as a response to the ServerCommand
 * GetBookingsByPhoneNumberCommand and GetBookingsBySSNCommand,
 * returning a list of bookings.
 *
 * @author Anthon Lenander
 * @version 2021-03-02
 */
public class ReturnBookingsCommand implements ClientCommand {

    private final List<Booking> bookings;

    /**
     * Constructor for initializing the list of bookings of this instance
     *
     * @param bookings the list of bookings to set this instance's list of bookings to
     */
    public ReturnBookingsCommand(List<Booking> bookings) {
        this.bookings = bookings;
    }

    /**
     * Method for setting the bookings list of the client
     *
     * @param handler A reference to the ClientHandler object
     * @throws IOException If an input or output exception occurred
     */
    @Override
    public void execute(ClientHandler handler) throws IOException {
        handler.setBookings(this.bookings);
    }
}