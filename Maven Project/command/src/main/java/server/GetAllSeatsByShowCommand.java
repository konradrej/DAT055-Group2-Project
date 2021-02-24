package server;

import client.ReturnAllSeatsByShowCommand;

import java.io.*;
import java.util.*;

public class GetAllSeatsByShowCommand implements ServerCommand, Serializable {

    private Object show;

    public GetAllSeatsByShowCommand(Object show) { this.show = show; }

    @Override
    public void execute(ServerHandler handler, ObjectInputStream in, ObjectOutputStream out) throws IOException {

        Collection<Object> seatsByShow = Collections.emptyList();

        try {
            seatsByShow = (Collection<Object>)handler.getAllSeatsByShow(this.show);
        } catch (ClassCastException e) {
            System.err.println("Class could not be casted. Message: " + e.getMessage());
            e.printStackTrace();
        } finally {
            out.writeObject(new ReturnAllSeatsByShowCommand(
                    seatsByShow
            ));
        }

    }
}