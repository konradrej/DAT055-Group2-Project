import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class GetBookingsBySSNCommand implements ServerCommand, Serializable {

    private String SSN;

    public GetBookingsBySSNCommand(String SSN) { this.SSN = SSN; }

    @Override
    public void execute(ObjectOutputStream out, CinemaBookingSystem cbs) throws IOException {
        out.writeObject(new ResponseGetBookingsBySSNCommand());
        out.writeObject(cbs.findBookingsBySSN(this.SSN));
    }
}