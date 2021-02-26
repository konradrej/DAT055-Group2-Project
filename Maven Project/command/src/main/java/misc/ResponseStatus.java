package misc;

import java.io.Serializable;

public class ResponseStatus implements Serializable {
    private final boolean status;
    private final String message;

    public ResponseStatus(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public boolean getStatus(){
        return status;
    }

    public String getMessage(){
        return message;
    }
}