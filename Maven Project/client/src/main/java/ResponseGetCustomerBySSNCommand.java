import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class ResponseGetCustomerBySSNCommand implements ClientCommand, Serializable {

    public ResponseGetCustomerBySSNCommand(){}

    @Override
    public void execute(ObjectInputStream in, ClientModel c) throws IOException, ClassNotFoundException {
        ClientModel.getInstance(); //Discuss how to implement this
    }
}
