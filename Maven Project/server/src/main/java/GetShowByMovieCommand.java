import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class GetShowByMovieCommand implements ServerCommand, Serializable {

    public GetShowByMovieCommand(){}

    @Override
    public void execute(ObjectOutputStream out, CinemaBookingSystem cbs) throws IOException {
        out.writeObject(new ResponseGetShowByMovieCommand());
        //out.writeObject(cbs.getShowByMovie()); Discuss how to implement this
    }
}