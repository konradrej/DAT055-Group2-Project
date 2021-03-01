package client;

import misc.ResponseStatus;

import java.io.*;

public class ResponseCreateBookingCommand implements ClientCommand {

    private final ResponseStatus response;

    public ResponseCreateBookingCommand(ResponseStatus response) {
        this.response = response;
    }

    @Override
    public void execute(ClientHandler handler) throws IOException {
        handler.setResponseStatus(response);
    }
}