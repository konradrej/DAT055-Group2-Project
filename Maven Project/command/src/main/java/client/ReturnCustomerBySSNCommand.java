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

    /**
     * Constructor for initializing the customer instance variable of this instance
     *
     * @param customer the customer which is to be returned
     */
    public ReturnCustomerBySSNCommand(Customer customer) {
        this.customer = customer;
    }

    /**
     * Method for setting the customer of the client given an SSN in the ClientHandler
     *
     * @param handler A reference to the ClientHandler
     * @throws IOException
     */
    @Override
    public void execute(ClientHandler handler) throws IOException {
        handler.setCustomerBySSN(this.customer);
    }
}