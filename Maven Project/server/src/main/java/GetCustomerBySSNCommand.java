import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class GetCustomerBySSNCommand implements ServerCommand, Serializable {

    private String SSN;

    public GetCustomerBySSNCommand(String SSN) { this.SSN = SSN; }

    @Override
    public void execute(ObjectOutputStream out, CinemaBookingSystem cbs) throws IOException {
        out.writeObject(new ResponseGetCustomerBySSNCommand());
        out.writeObject(cbs.getCustomerBySSN(this.SSN));
    }
}
