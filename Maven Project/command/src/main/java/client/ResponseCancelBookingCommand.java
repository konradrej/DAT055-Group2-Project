package client;

import misc.ResponseStatus;

import java.io.IOException;


/**
 * This class implements the ClientCommand interface
 * and serves as a response to the ServerCommand
 * CancelBookingCommand, returning an instance of
 * ResponseStatus containing information about
 * the execution of the command.
 *
 * @author Anthon Lenander
 * @version 2021-03-02
 */
public class ResponseCancelBookingCommand implements ClientCommand {

    private final ResponseStatus response;

    /**
     * Constructor for initializing the ResponseCancelBookingCommand instance
     *
     * @param response the response from the cancel booking method call
     */
    public ResponseCancelBookingCommand(ResponseStatus response) {
        this.response = response;
    }

    /**
     * Method that sets the responseStatus of the ClientHandler from the called command
     *
     * @param handler A reference to the ClientHandler, to call methods from the ClientHandler instance
     * @throws IOException If an input or output exception occurred
     */
    @Override
    public void execute(ClientHandler handler) throws IOException {
        handler.setResponseStatus(response);
    }

}