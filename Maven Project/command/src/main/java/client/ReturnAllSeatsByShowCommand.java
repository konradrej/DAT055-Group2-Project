package client;
;

import java.io.*;
import java.util.ArrayList;

public class ReturnAllSeatsByShowCommand implements ClientCommand {

    private ArrayList<Object> allSeatsByShow;

    public ReturnAllSeatsByShowCommand(ArrayList<Object> allSeatsByShow) { this.allSeatsByShow = allSeatsByShow; }

    @Override
    public void execute(ClientHandler handler, ObjectInputStream in, ObjectOutputStream out) throws IOException, ClassNotFoundException {
        handler.setSeatsByShow(allSeatsByShow);
    }
}
