package client;

import java.io.*;

public class ResponseCreateBookingCommand implements ClientCommand, Serializable {

    private String statusMessage;

    public ResponseCreateBookingCommand(String statusMessage) { this.statusMessage = statusMessage; }

    @Override
    public void execute(ClientHandler handler, ObjectInputStream in, ObjectOutputStream out) throws IOException, ClassNotFoundException {
        //ClientModel.getInstance(); //Discuss implementation of this in ClientModel
    }
}