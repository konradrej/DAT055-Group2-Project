import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class CreateCustomerCommand implements ServerCommand, Serializable {

    private String name, phoneNumber, SSN;

    public CreateCustomerCommand(String name, String phoneNumber, String SSN)
    {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.SSN = SSN;
    }

    @Override
    public void execute(ObjectOutputStream out, CinemaBookingSystem cbs) throws IOException {
        out.writeObject(new ResponseCreateCustomerCommand());
        out.writeObject(cbs.createCustomer(this.name, this.phoneNumber, this.SSN));
    }
}