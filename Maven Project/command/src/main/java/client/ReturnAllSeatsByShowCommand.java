package client;

import client.ClientCommand;
import client.ClientHandler;

import java.io.*;
import java.util.*;

public class ReturnAllSeatsByShowCommand implements ClientCommand, Serializable {

    private Collection<Object> allSeatsByShow;

    public ReturnAllSeatsByShowCommand(Collection<Object> allSeatsByShow) { this.allSeatsByShow = allSeatsByShow; }

    @Override
    public void execute(ClientHandler handler, ObjectInputStream in, ObjectOutputStream out) throws IOException, ClassNotFoundException {
        handler.setSeatsByShow(allSeatsByShow);
    }
}
