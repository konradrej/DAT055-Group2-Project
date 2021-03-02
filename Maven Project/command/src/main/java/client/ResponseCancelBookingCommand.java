package client;

import misc.ResponseStatus;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * This class implements the ClientCommand interface
 * and serves as a response to the ServerCommand
 * CancelBookingCommand, returning an instance of
 * ResponseStatus containing information about
 * the execution of the command.
 *
 * @author Anthon Lenander
 * @version 2021-02-03
 */
public class ResponseCancelBookingCommand implements ClientCommand {

    private final ResponseStatus response;

    public ResponseCancelBookingCommand(ResponseStatus response) {
        this.response = response;
    }

    @Override
    public void execute(ClientHandler handler) throws IOException {
        handler.setResponseStatus(response);
    }
}