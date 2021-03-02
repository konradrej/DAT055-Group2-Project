package client;

import misc.ResponseStatus;

import java.io.*;

/**
 * This class implements the ClientCommand interface
 * and serves as a response to the ServerCommand
 * CreateBookingCommand, returning an instance of
 * ResponseStatus containing information about
 * the execution of the command.
 *
 * @author Anthon Lenander
 * @version 2021-02-03
 */
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