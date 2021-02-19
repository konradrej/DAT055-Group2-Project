import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class GetShowByMovie implements ServerCommand, Serializable {
    public GetMoviesCommand(){}
    @Override
    public void execute(ObjectOutputStream out, CinemaBookingSystem cbs) throws IOException {
        out.writeObject(new ResponseGetMoviesCommand());
        out.writeObject(cbs.getMovieCollection());
    }
}