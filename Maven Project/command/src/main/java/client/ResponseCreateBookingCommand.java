package client;

import java.io.*;

public class ResponseCreateBookingCommand implements ClientCommand {

    private ResponseStatus response;

    public ResponseCreateBookingCommand(ResponseStatus response) {
        this.response = response;
    }

    @Override
    public void execute(ClientHandler handler, ObjectInputStream in, ObjectOutputStream out) throws IOException, ClassNotFoundException {
        //ClientModel.getInstance(); //Discuss implementation of this in ClientModel
    }
}