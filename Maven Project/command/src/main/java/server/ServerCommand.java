package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public interface ServerCommand extends Serializable {
     void execute(ServerHandler handler, ObjectOutputStream out) throws IOException, ClassNotFoundException;
}