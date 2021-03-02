package server;

import cinemaObjects.Booking;
import client.ResponseCancelBookingCommand;
import misc.ResponseStatus;

import java.awt.print.Book;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * This class implements the ServerCommand interface,
 * and cancels given booking aswell as
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
        handler.cancelBooking(this.booking);

        out.writeObject(new ResponseCancelBookingCommand(
                new ResponseStatus(true, "Booking cancelled")
        ));
    }
}