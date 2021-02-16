import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Handles connection establishment, creates a new
 * ConnectionHandler thread for each active connection.
 *
 * @author Konrad Rej
 * @version 0.1.0
 */
public class SocketServerCommunication extends Thread {
    /**
     * Instance variable for singleton design pattern.
     */
    private static SocketServerCommunication INSTANCE;

    /**
     * Empty constructor.
     */
    private SocketServerCommunication() { }

    /**
     * Retrieves singleton instance of SockerCommunication.
     *
     * @return SocketCommunication instance
     */
    public static SocketServerCommunication getInstance(){
        if(INSTANCE == null){
            INSTANCE = new SocketServerCommunication();
        }

        return INSTANCE;
    }

    /**
     *  Starts listening on port 888 for connections and
     *  starts a new ConnectionHandler thread for each
     *  established connection.
     */
    @Override
    public void run(){
        try(ServerSocket ss = new ServerSocket(888)) {
            Socket s;

            while(true){
                s = ss.accept();
                System.out.println("Connection established.");

                Thread handler = new ConnectionHandler(s);
                handler.start();
            }
        } catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }
    }
}