import server.ServerCommand;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

/**
 * Class handling using socket to receive and send commands.
 *
 * @author Konrad Rej
 * @version 2021-03-01
 */
public class ConnectionHandler implements Runnable {
    private final Socket socket;
    private final CinemaBookingSystem cbs = CinemaBookingSystem.getInstance();

    /**
     * Constructor for initializing the ConnectionHandler instance.
     *
     * @param socket Opened socket connection
     */
    public ConnectionHandler(Socket socket) {
        this.socket = socket;
    }

    /**
     * Establish read/write streams through the socket.
     */
    @Override
    public void run() {
        try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {

            while (socket.isConnected()) {
                ServerCommand command = (ServerCommand) in.readObject();
                command.execute(cbs, out);
            }
        } catch (ClassCastException e) {
            System.err.println("Class could not be cast to ClientCommand.");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Class could not be found.");
            e.printStackTrace();
        } catch (NotSerializableException e) {
            System.err.println("Class is not serializable.");
            e.printStackTrace();
        } catch (SocketException e) {
            System.out.println("Connection reset.");
        } catch (IOException e) {
            System.err.println("IOException occurred. Message: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (!socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException e) {
                System.err.println("Error closing socket. Message: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
