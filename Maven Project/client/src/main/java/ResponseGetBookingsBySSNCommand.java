import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class ResponseGetBookingsBySSNCommand implements ClientCommand, Serializable {

    public ResponseGetBookingsBySSNCommand(){}

    @Override
    public void execute(ObjectInputStream in, ClientModel c) throws IOException, ClassNotFoundException {
        ClientModel.getInstance(); //Discuss how to implement this in the ClientModel
    }
}