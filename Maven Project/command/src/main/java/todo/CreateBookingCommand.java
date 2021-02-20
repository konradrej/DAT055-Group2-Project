package todo;

import server.ServerCommand;
import server.ServerHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class CreateBookingCommand implements ServerCommand, Serializable {

    //private Show show;
    //private Customer customer;
    //private Collection<Row> rows;

    //public todo.CreateBookingCommand(Show show, Customer customer, Collection<Row> rows)
    //{
    //    this.show = show;
    //    this.customer = customer;
    //    this.rows = rows;
    //}

    @Override
    public void execute(ServerHandler handler, ObjectInputStream in, ObjectOutputStream out) throws IOException {
        out.writeObject(new ResponseCreateBookingCommand());
        //out.writeObject(cbs.createBooking(this.show, this.customer, this.rows));
    }
}