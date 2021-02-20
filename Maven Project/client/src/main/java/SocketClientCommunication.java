import client.ClientCommand;
import server.ServerCommand;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public enum SocketClientCommunication implements Runnable {
    INSTANCE();

    SocketClientCommunication() {
    }

    public static SocketClientCommunication getInstance() {
        return INSTANCE;
    }

    private String ip = "localhost";
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Socket socket;

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void sendCommand(ServerCommand command){
        try {
            this.out.writeObject(command);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            this.socket = new Socket(ip, 888);
            System.out.println("Connection established.");
        }catch (IOException e){
            System.err.println("Connection could not be established.");
            return;
        }
        try {

            out = new ObjectOutputStream(socket.getOutputStream());

            in = new ObjectInputStream(this.socket.getInputStream());


            while(socket.isConnected()){
                ClientCommand command = (ClientCommand) in.readObject();
                command.execute(ClientModel.getInstance(), in, out);

            }
        } catch (EOFException e){
            closeConnection();
        } catch (SocketException e) {
            //Something else?
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
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