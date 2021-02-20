package todo;

import client.ClientCommand;
import client.ClientHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ResponseCreateCustomerCommand implements ClientCommand, Serializable {

    public ResponseCreateCustomerCommand(){}

    @Override
    public void execute(ClientHandler handler, ObjectInputStream in, ObjectOutputStream out) throws IOException, ClassNotFoundException {
        //ClientModel.getInstance(); //Discuss implementation of this in ClientModel
    }
}
