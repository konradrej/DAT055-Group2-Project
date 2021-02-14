import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerCommunication extends Thread {
    private static ServerCommunication INSTANCE;
    private Socket socket;
    private BufferedReader br;
    private DataOutputStream dos;

    private ServerCommunication() {
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                closeConnection();
            }
        });
    }

    public synchronized static ServerCommunication getInstance(){
        if(INSTANCE == null){
            INSTANCE = new ServerCommunication();
        }

        return INSTANCE;
    }

    public void sendMessage(String message){
        try {
            this.dos.writeBytes(message + "\n");
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

            // to send data to the server
            this.dos = new DataOutputStream(
                    this.socket.getOutputStream()
            );

            // to read data coming from the server
            this.br = new BufferedReader(
                    new InputStreamReader(
                            this.socket.getInputStream()
                    )
            );

            String str;
            while ((str = br.readLine()) != null) {
                // receive from the server
                System.out.println(str);

                if(str.substring(0, str.indexOf("(")).equals("movieNames")){
                    String[] movies = str.substring(str.indexOf("(")+1, str.indexOf(")")-1).split(",");

                    for(String movie : movies){
                        System.out.println(movie);
                    }
                }
            }

            closeConnection();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void closeConnection(){
        try {
            if(socket != null){
                System.out.println("Connection closed.");
                this.dos.close();
                this.br.close();
                this.socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
