package client;

import misc.ResponseStatus;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ResponseCancelBookingCommand implements ClientCommand{

    private final ResponseStatus response;

    public ResponseCancelBookingCommand(ResponseStatus response){
        this.response = response;
    }

    @Override
    public void execute(ClientHandler handler) throws IOException {
        handler.setResponseStatus(response);
    }
}
