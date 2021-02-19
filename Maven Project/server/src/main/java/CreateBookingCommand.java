import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;

public class CreateBookingCommand implements ServerCommand, Serializable {

    private Show show;
    private Customer customer;
    private Collection<Row> rows;

    public CreateBookingCommand(Show show, Customer customer, Collection<Row> rows)
    {
        this.show = show;
        this.customer = customer;
        this.rows = rows;
    }

    @Override
    public void execute(ObjectOutputStream out, CinemaBookingSystem cbs) throws IOException {
        out.writeObject(new ResponseCreateBookingCommand());
        out.writeObject(cbs.createBooking(this.show, this.customer, this.rows));
    }
}