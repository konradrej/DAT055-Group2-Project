package server;

import client.ReturnAllSeatsByShowCommand;

import java.io.*;
import java.util.ArrayList;

public class GetAllSeatsByShowCommand implements ServerCommand {

    private Object show;

    public GetAllSeatsByShowCommand(Object show) { this.show = show; }

    @Override
    public void execute(ServerHandler handler, ObjectInputStream in, ObjectOutputStream out) throws IOException {

        ArrayList<Object> seatsByShow = new ArrayList <> ();

        try {
            seatsByShow = (ArrayList<Object>)handler.getAllSeatsByShow(this.show);
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
