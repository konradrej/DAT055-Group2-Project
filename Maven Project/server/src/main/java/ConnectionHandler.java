import java.io.*;
import java.net.Socket;

/**
 * @author Konrad Rej
 * @version 0.0.1
 */
public class ConnectionHandler extends Thread {
    private final Socket socket;
    private BufferedReader br;
    private PrintStream ps;

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
     * Esthbalise read/write streams through the socket.
     */
    @Override
    public void run() {
        try {
            ps = new PrintStream(
                    socket.getOutputStream()
            );

            br = new BufferedReader(
                    new InputStreamReader(
                            this.socket.getInputStream()
                    )
            );

            String str;

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

            closeConnection();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void closeConnection(){
        try {
            System.out.println("Connection closed.");
            this.ps.close();
            this.br.close();
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
