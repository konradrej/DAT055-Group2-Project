package client;

import java.io.IOException;
import java.io.Serializable;

/**
 * Contains method which executes the commands function.
 *
 * @author Konrad Rej
 * @version 2021-03-02
 */
public interface ClientCommand extends Serializable {
    void execute(ClientHandler handler) throws IOException;
}