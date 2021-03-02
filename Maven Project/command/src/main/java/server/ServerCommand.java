package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Contains method which executes the commands function.
 *
 * @author Konrad Rej
 * @version 2021-03-02
 */
public interface ServerCommand extends Serializable {
    void execute(ServerHandler handler, ObjectOutputStream out) throws IOException;
}