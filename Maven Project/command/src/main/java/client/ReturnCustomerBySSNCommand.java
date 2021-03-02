package client;

import cinemaObjects.Customer;

import java.io.*;

/**
 * This class implements the ClientCommand interface
 * and serves as a response to the ServerCommand
 * GetCustomerBySSNCommand, returning an instance of
 * Customer, where the Customer instance has the given SSN.
 *
 * @author Anthon Lenander
 * @version 2021-02-03
 */
public class ReturnCustomerBySSNCommand implements ClientCommand {

    private final Customer customer;

    public ReturnCustomerBySSNCommand(Customer customer) { this.customer = customer; }

    @Override
    public void execute(ClientHandler handler) throws IOException {
        handler.setCustomerBySSN(this.customer);
    }
}
