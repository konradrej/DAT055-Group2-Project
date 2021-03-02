package server;

import cinemaObjects.Customer;
import cinemaObjects.Row;
import cinemaObjects.Show;
import client.ResponseCreateBookingCommand;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the ServerCommand interface,
 * and creates a new booking aswell as
 * returning an instance of ResponseStatus containing
 * information about the execution of the command
 * to the ResponseCreateBookingCommand class.
 *
 * @author Anthon Lenander
 * @version 2021-02-03
 */
public class CreateBookingCommand implements ServerCommand {

    private final Show show;
    private final Customer customer;
    private final List<Row> rows;

    /**
     * Constructor for initializing all variables needed to create a new booking
     *
     * @param show     which show the booking is for
     * @param customer the customer that has booked the show
     * @param rows     A list of all the rows the booking has booked seats in
     */
    public CreateBookingCommand(Show show, Customer customer, List<Row> rows) {
        this.show = show;
        this.customer = customer;
        this.rows = rows;
    }

    /**
     * Method for creating a new booking
     *
     * @param handler A reference to the ServerHandler
     * @param out     A reference to the ObjectOutputStream
     * @throws IOException
     */
    @Override
    public void execute(ServerHandler handler, ObjectOutputStream out) throws IOException {
        out.writeObject(new ResponseCreateBookingCommand(
                handler.createBooking(this.show, this.customer, this.rows)
        ));
    }
}