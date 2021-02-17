import java.io.IOException;
import java.io.ObjectOutputStream;


public interface ServerCommand {
     void execute(ObjectOutputStream s, CinemaBookingSystem cbs) throws IOException;

}