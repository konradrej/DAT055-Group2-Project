package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ReturnShowsByMovieCommand implements ClientCommand {

    private Object showCollection;

    public ReturnShowsByMovieCommand(Object showCollection) {
        this.showCollection = showCollection;
    }

    @Override
    public void execute(ClientHandler handler, ObjectInputStream in, ObjectOutputStream out) throws IOException, ClassNotFoundException {
        handler.setShowCollection(showCollection);
    }
}
