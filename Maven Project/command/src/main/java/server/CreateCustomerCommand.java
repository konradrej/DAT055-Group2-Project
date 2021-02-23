package server;

import server.ServerCommand;
import server.ServerHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
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
    public void execute(ServerHandler handler, ObjectInputStream in, ObjectOutputStream out) throws IOException {
        handler.createCustomer(this.name, this.phoneNumber, this.SSN);
    }
}