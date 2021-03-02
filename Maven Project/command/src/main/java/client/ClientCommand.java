package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public interface ClientCommand extends Serializable {
     void execute(ClientHandler handler) throws IOException;
}