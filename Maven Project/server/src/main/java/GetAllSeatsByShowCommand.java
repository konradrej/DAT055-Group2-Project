import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class GetAllSeatsByShowCommand implements ServerCommand, Serializable {

    private Show show;

    public GetAllSeatsByShowCommand(Show show) { this.show = show; }

    @Override
    public void execute(ObjectOutputStream out, CinemaBookingSystem cbs) throws IOException {
        out.writeObject(new ResponseGetAllSeatsByShowCommand());
        out.writeObject(cbs.getAllSeatsByShow(this.show));
    }
}
