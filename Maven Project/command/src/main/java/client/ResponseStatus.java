package client;

import client.ClientCommand;
import client.ClientHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ResponseStatus implements ClientCommand {
    private boolean status;
    private String message;

    public ResponseStatus(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public void execute(ClientHandler handler, ObjectInputStream in, ObjectOutputStream out) throws IOException, ClassNotFoundException {

    }
}