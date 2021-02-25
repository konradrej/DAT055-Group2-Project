package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ResponseCancelBookingCommand implements ClientCommand{

    private ResponseStatus response;

    public ResponseCancelBookingCommand(ResponseStatus response){
        this.response = response;
    }

    @Override
    public void execute(ClientHandler handler, ObjectInputStream in, ObjectOutputStream out) throws IOException, ClassNotFoundException {

    }
}
