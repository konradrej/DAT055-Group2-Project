import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Handles connection establishment, creates a new
 * ConnectionHandler thread for each active connection.
 *
 * @author Konrad Rej
 * @version 2021-03-03
 */
public enum SocketServerCommunication implements Runnable {
    INSTANCE();

    SocketServerCommunication() {
        Runtime.getRuntime().addShutdownHook(new Thread(() ->
                listenForConnection = false));
    }

    /**
     * Retrieves singleton instance of SocketCommunication.
     *
     * @return SocketCommunication instance
     */
    public static SocketServerCommunication getInstance() {
        return INSTANCE;
    }

    boolean listenForConnection = true;

    /**
     * Starts listening for connections and starts a
     * new ConnectionHandler thread for each established
     * connection.
     */
    @Override
    public void run() {
        int port = 888;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            Socket socket;

            while (listenForConnection) {
                socket = serverSocket.accept();
                System.out.println("Connection established.");

                new Thread(new ConnectionHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Could not listen for connections on port " + port + ". Exiting...");
            JOptionPane.showMessageDialog(null, "Could not listen for connections on port " + port + ".\nPress OK to exit.", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
}