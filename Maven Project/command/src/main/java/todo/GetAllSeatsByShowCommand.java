package todo;

import server.ServerCommand;
import server.ServerHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class GetAllSeatsByShowCommand implements ServerCommand, Serializable {

    //private Show show;

    //public todo.GetAllSeatsByShowCommand(Show show) { this.show = show; }

    @Override
    public void execute(ServerHandler handler, ObjectInputStream in, ObjectOutputStream out) throws IOException {
        /*out.writeObject(new ResponseGetAllSeatsByShowCommand(
                handler.getAllSeatsByShow()
        ));*/
    }
}
