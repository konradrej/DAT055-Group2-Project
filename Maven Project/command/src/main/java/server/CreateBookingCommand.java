package server;

import cinemaObjects.Customer;
import cinemaObjects.Row;
import cinemaObjects.Show;
import client.ResponseCreateBookingCommand;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CreateBookingCommand implements ServerCommand {

    private final Show show;
    private final Customer customer;
    private final List<Row> rows;

    public CreateBookingCommand(Show show, Customer customer, List<Row> rows)
    {
        this.show = show;
        this.customer = customer;
        this.rows = rows;
    }

    @Override
    public void execute(ServerHandler handler, ObjectOutputStream out) throws IOException {
        out.writeObject(new ResponseCreateBookingCommand(
                handler.createBooking(this.show, this.customer, this.rows)
        ));
    }
}