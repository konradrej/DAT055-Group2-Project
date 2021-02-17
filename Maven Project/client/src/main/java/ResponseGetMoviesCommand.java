import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class ResponseGetMoviesCommand implements ClientCommand, Serializable {

    public ResponseGetMoviesCommand(){}
    @Override
    public void execute(ObjectInputStream in, ClientModel c) throws IOException, ClassNotFoundException {
        ClientModel.getInstance().setMovieCollection((MovieCollection) in.readObject());
    }
}