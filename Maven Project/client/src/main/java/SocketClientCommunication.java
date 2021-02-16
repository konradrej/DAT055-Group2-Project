import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class SocketClientCommunication extends Thread {
    private static SocketClientCommunication INSTANCE;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    private SocketClientCommunication() {
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                closeConnection();
            }
        });
    }

    public synchronized static SocketClientCommunication getInstance(){
        if(INSTANCE == null){
            INSTANCE = new SocketClientCommunication();
        }

        return INSTANCE;
    }

    public void sendCommand(SocketCommands command){
        try {
            this.out.writeObject(command);
            out.flush();
        } catch (IOException e) { }
    }

    @Override
    public void run() {
        try {
            try {
                this.socket = new Socket("localhost", 888);
                System.out.println("Connection established.");
            }catch (IOException e){
                System.err.println("Connection could not be established.");
                return;
            }

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
                    case responseGetMovies:
                        ClientModel.getInstance().setMovieCollection((MovieCollection) in.readObject());
                        break;
                }
            }
        } catch (EOFException e){
            closeConnection();
        } catch (SocketException e) {

        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();

            System.err.println("ERROR OR HERE");
        }
    }

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
