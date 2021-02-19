import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class ResponseCreateCustomerCommand implements ClientCommand, Serializable {

    public ResponseCreateCustomerCommand(){}

    @Override
    public void execute(ObjectInputStream in, ClientModel c) throws IOException, ClassNotFoundException {
        ClientModel.getInstance(); //Discuss implementation of this in ClientModel
    }
}
