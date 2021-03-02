package client;

import cinemaObjects.Customer;

import java.io.*;

public class ReturnCustomerBySSNCommand implements ClientCommand {

    private final Customer customer;

    public ReturnCustomerBySSNCommand(Customer customer) { this.customer = customer; }

    @Override
    public void execute(ClientHandler handler) throws IOException {
        handler.setCustomerBySSN(this.customer);
    }
}
