package server;

import client.ReturnCustomerBySSNCommand;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class GetCustomerBySSNCommand implements ServerCommand, Serializable {

    private String SSN;

    public GetCustomerBySSNCommand(String SSN) { this.SSN = SSN; }

    @Override
    public void execute(ServerHandler handler, ObjectInputStream in, ObjectOutputStream out) throws IOException {
        out.writeObject(new ReturnCustomerBySSNCommand(
                handler.getCustomerBySSN(SSN)
        ));
    }
}
