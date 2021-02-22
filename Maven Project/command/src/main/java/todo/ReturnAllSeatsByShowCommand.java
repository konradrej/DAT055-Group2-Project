package todo;

import client.ClientCommand;
import client.ClientHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ReturnAllSeatsByShowCommand implements ClientCommand, Serializable {

    //private Object show;

    //public ReturnAllSeatsByShowCommand(Object show) { this.show = show; }

    @Override
    public void execute(ClientHandler handler, ObjectInputStream in, ObjectOutputStream out) throws IOException, ClassNotFoundException {
        //ClientModel.getInstance(); //Discuss how to implement this in the ClientModel
        //handler.getAllSeatsByShow(this.show);
    }
}
