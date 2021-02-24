package client;

import java.io.*;

public class ReturnCustomerBySSNCommand implements ClientCommand {

    private Object customer;

    public ReturnCustomerBySSNCommand(Object customer) { this.customer = customer; }

    @Override
    public void execute(ClientHandler handler, ObjectInputStream in, ObjectOutputStream out) throws IOException, ClassNotFoundException {
        handler.setCustomerBySSN(this.customer);
    }
}
