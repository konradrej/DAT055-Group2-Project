import java.io.*;
import java.net.Socket;

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

    public void sendMessage(String message){
        try {
            this.out.writeBytes(message + "\n");
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
                throw e;
            }

            out = new ObjectOutputStream(
                    socket.getOutputStream()
            );

            in = new ObjectInputStream(
                    this.socket.getInputStream()
            );

            while(true){

            }

            /*
            String str;
            while ((str = in.readLine()) != null) {
                // receive from the server
                System.out.println(str);

                if(str.substring(0, str.indexOf("(")).equals("movieNames")){
                    String[] movies = str.substring(str.indexOf("(")+1, str.indexOf(")")-1).split(",");

                    for(String movie : movies){
                        System.out.println(movie);
                    }
                }
            }
            */

            closeConnection();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void closeConnection(){
        try {
            if(socket != null){
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
