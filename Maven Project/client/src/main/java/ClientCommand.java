import java.io.IOException;
import java.io.ObjectInputStream;

public interface ClientCommand {
     void execute(ObjectInputStream s, ClientModel c) throws IOException, ClassNotFoundException;
}