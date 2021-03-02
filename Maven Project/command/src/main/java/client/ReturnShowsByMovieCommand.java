package client;

import collections.ShowCollection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ReturnShowsByMovieCommand implements ClientCommand {

    private final ShowCollection showCollection;

    public ReturnShowsByMovieCommand(ShowCollection showCollection) {
        this.showCollection = showCollection;
    }

    @Override
    public void execute(ClientHandler handler) throws IOException {
        handler.setShowCollection(showCollection);
    }
}
