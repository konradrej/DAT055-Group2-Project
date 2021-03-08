package misc;

import java.io.Serializable;

/**
 * This class implements the Serializable interface
 * and serves as a response sent with each command
 * implementing this class as a return type.
 *
 * @author Anthon Lenander
 * @version 2021-03-08
 */
public class ResponseStatus implements Serializable {
    private final boolean status;
    private final String message;

    /**
     * Constructor for initializing a ResponseStatus instance
     *
     * @param status  the status of the command
     * @param message the message sent with the status
     */
    public ResponseStatus(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    /**
     * Method for getting the status of this instance
     *
     * @return returns this instance's status
     */
    public boolean getStatus() {
        return status;
    }

    /**
     * Method for getting the message of this instance
     *
     * @return returns this instance's message
     */
    public String getMessage() {
        return message;
    }

}