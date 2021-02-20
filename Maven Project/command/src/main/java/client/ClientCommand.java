package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public interface ClientCommand {
     void execute(ClientHandler handler, ObjectInputStream in, ObjectOutputStream out) throws IOException, ClassNotFoundException;
}