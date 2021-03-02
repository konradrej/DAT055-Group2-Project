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
 * @version 2021-02-03
 */
public class CancelBookingCommand implements ServerCommand {

    private final Booking booking;

    public CancelBookingCommand(Booking booking)
    {
        this.booking = booking;
    }

    @Override
    public void execute(ServerHandler handler, ObjectOutputStream out) throws IOException {
        handler.cancelBooking(this.booking);

        out.writeObject(new ResponseCancelBookingCommand(
                new ResponseStatus(true,"Booking cancelled")
        ));
    }
}
