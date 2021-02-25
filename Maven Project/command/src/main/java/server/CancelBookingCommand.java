package server;

import client.ResponseCancelBookingCommand;
import client.ResponseCreateBookingCommand;
import client.ResponseStatus;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class CancelBookingCommand implements ServerCommand{

    private Object booking;

    public CancelBookingCommand(Object booking)
    {
        this.booking = booking;
    }


    @Override
    public void execute(ServerHandler handler, ObjectInputStream in, ObjectOutputStream out) throws IOException {

        handler.cancelBooking(this.booking);

        out.writeObject(new ResponseCancelBookingCommand(
                new ResponseStatus(true,"Booking cancelled")

        ));
    }
}
