import server.ServerCommand;

import java.io.*;
import java.net.Socket;

/**
 * @author Konrad Rej
 * @version 0.0.2
 */
public class ConnectionHandler implements Runnable {
    private final Socket socket;
    private final CinemaBookingSystem cbs = CinemaBookingSystem.getInstance();

    /**
     * Constructor for initializing the ConnectionHandler instance.
     *
     * @param socket    Opened socket connection
     */
    public ConnectionHandler(Socket socket){
        this.socket = socket;
    }

    /**
     * Establish read/write streams through the socket.
     */
    @Override
    public void run() {
        try(ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())){

            while(socket.isConnected()){
                ServerCommand command = (ServerCommand) in.readObject();
                command.execute(cbs, in, out);
            }
        } catch(ClassCastException e){
            System.err.println("Class could not be cast to client.ClientCommand.");
            e.printStackTrace();
        } catch(ClassNotFoundException e) {
            System.err.println("Class could not be found.");
            e.printStackTrace();
        } catch(NotSerializableException e) {
            System.err.println("Class is not serializable.");
            e.printStackTrace();
        } catch (IOException e){
            System.err.println("IOException occurred. Message: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if(!socket.isClosed()){
                    socket.close();
                }
            } catch (IOException e){
                System.err.println("Error closing socket. Message: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
