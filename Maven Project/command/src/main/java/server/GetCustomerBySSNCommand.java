package server;

import client.ReturnCustomerBySSNCommand;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * This class implements the ServerCommand interface,
 * and is used to get a customer given a social security number,
 * as well as calling the ReturnCustomerBySSNCommand with
 * the social security number.
 *
 * @author Anthon Lenander
 * @version 2021-02-03
 */
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
