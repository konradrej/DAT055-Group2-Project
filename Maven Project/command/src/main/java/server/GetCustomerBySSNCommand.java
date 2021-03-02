package server;

import client.ReturnCustomerBySSNCommand;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GetCustomerBySSNCommand implements ServerCommand {

    private final String SSN;

    public GetCustomerBySSNCommand(String SSN) { this.SSN = SSN; }

    @Override
    public void execute(ServerHandler handler, ObjectOutputStream out) throws IOException {
        out.writeObject(new ReturnCustomerBySSNCommand(
                handler.getCustomerBySSN(SSN)
        ));
    }
}
