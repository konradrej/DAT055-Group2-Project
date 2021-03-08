package server;

import cinemaObjects.Booking;
import client.ResponseCancelBookingCommand;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * This class implements the ServerCommand interface,
 * and cancels given booking as well as
 * returning an instance of ResponseStatus containing
 * information about the execution of the command
 * to the ResponseCancelBookingCommand class.
 *
 * @author Anthon Lenander
 * @version 2021-03-02
 */
public class CancelBookingCommand implements ServerCommand {

    private final Booking booking;

    /**
     * Constructor for initializing the booking which is to be cancelled
     *
     * @param booking the booking which is to be cancelled
     */
    public CancelBookingCommand(Booking booking) {
        this.booking = booking;
    }

    /**
     * Method for cancelling a booking
     *
     * @param handler A reference to the ServerHandler
     * @param out     A reference to the ObjectOutputStream
     * @throws IOException If an input or output exception occurred
     */
    @Override
    public void execute(ServerHandler handler, ObjectOutputStream out) throws IOException {
        out.writeObject(new ResponseCancelBookingCommand(
                handler.cancelBooking(this.booking)

        ));

    }
}