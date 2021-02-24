package server;

import client.ResponseCreateBookingCommand;

import java.io.*;
import java.util.*;

public class CreateBookingCommand implements ServerCommand {

    private Object show;
    private Object customer;
    private Collection<Object> rows;

    public CreateBookingCommand(Object show, Object customer, Collection<Object> rows)
    {
        this.show = show;
        this.customer = customer;
        this.rows = rows;
    }

    @Override
    public void execute(ServerHandler handler, ObjectInputStream in, ObjectOutputStream out) throws IOException {
        out.writeObject(new ResponseCreateBookingCommand(
                handler.createBooking(this.show, this.customer, this.rows)
        ));
    }
}