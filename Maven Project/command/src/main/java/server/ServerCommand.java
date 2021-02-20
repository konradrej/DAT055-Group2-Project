package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public interface ServerCommand {
     void execute(ServerHandler handler, ObjectInputStream in, ObjectOutputStream out) throws IOException, ClassNotFoundException;
}