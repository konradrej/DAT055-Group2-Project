import java.io.*;
import java.net.Socket;

/**
 * @author Konrad Rej
 * @version 0.0.1
 */
public class ConnectionHandler extends Thread {
    private final Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

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

            String str;

            /*
            // TODO finish input/output handling.
            while ((str = br.readLine()) != null) {
                // Handle input/output through socket
                System.out.println(str);

                String toSend = null;
                switch(str){
                    case "loadMovieNames":
                        toSend = "movieNames(The Matrix,Daredevil)";
                        break;
                }

                if(toSend != null){
                    ps.println(toSend);
                }
            }
            */

            closeConnection();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void closeConnection(){
        try {
            out.flush();
            System.out.println("Connection closed.");
            this.out.close();
            this.in.close();
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
