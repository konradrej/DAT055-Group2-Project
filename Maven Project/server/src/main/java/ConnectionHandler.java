import java.io.*;
import java.net.Socket;

/**
 * @author Konrad Rej
 * @version 0.0.2
 */
public class ConnectionHandler extends Thread {
    private final Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    private CinemaBookingSystem cbs = CinemaBookingSystem.getInstance();

    /**
     * Constructor for initializing the ConnectionHandler instance.
     *
     * @param socket    Opened socket connection
     */
    public ConnectionHandler(Socket socket){
        this.socket = socket;

        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                closeConnection();
            }
        });
    }

    /**
     * Establish read/write streams through the socket.
     */
    @Override
    public void run() {
        try {
            out = new ObjectOutputStream(
                    socket.getOutputStream()
            );

            in = new ObjectInputStream(
                    this.socket.getInputStream()
            );

            SocketCommands str;
            while(socket.isConnected()){
                str = (SocketCommands) in.readObject();

                switch(str){
                    case getMovies:
                        out.writeObject(SocketCommands.responseGetMovies);
                        out.writeObject(cbs.getMovieCollection());
                        break;
                }
            }
        } catch (EOFException e){
            closeConnection();
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    /**
     * Gracefully closes connection and informs user.
     */
    public void closeConnection(){
        try {
            if(!socket.isClosed()){
                out.flush();
                System.out.println("Connection closed.");
                this.out.close();
                this.in.close();
                this.socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
