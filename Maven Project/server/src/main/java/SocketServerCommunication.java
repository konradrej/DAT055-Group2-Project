import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Handles connection establishment, creates a new
 * ConnectionHandler thread for each active connection.
 *
 * @author Konrad Rej
 * @version 2021-03-01
 */
public enum SocketServerCommunication implements Runnable {
    INSTANCE();

    SocketServerCommunication() {
        Runtime.getRuntime().addShutdownHook(new Thread(() ->
                listenForConnection = false));
    }

    boolean listenForConnection = true;

    /**
     * Retrieves singleton instance of SockerCommunication.
     *
     * @return SocketCommunication instance
     */
    public static SocketServerCommunication getInstance() {
        return INSTANCE;
    }

    /**
     * Starts listening on port 888 for connections and
     * starts a new ConnectionHandler thread for each
     * established connection.
     */
    @Override
    public void run() {
        try (ServerSocket ss = new ServerSocket(888)) {
            Socket s;

            while (listenForConnection) {
                s = ss.accept();
                System.out.println("Connection established.");

                new Thread(new ConnectionHandler(s)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Could not listen for connections on port 888. Exiting...");
            System.exit(1);
        }
    }
}