import java.io.*;
import java.net.Socket;

public enum SocketClientCommunication implements Runnable {
    INSTANCE();

    SocketClientCommunication(){ }

    public static SocketClientCommunication getInstance(){
        return INSTANCE;
    }

    private String ip = "localhost";
    private ObjectOutputStream out;

    public void setIp(String ip){
        this.ip = ip;
    }

    public void sendCommand(ServerCommand command){
        try{
            if(out != null){
                out.writeObject(command);
            }
        } catch(IOException e){
            System.err.println("Command could not be sent.");
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try(Socket socket = new Socket(ip, 888)) {
            System.out.println("Connection established.");

            try(ObjectInputStream in = new ObjectInputStream(socket.getInputStream())){
                out = new ObjectOutputStream(socket.getOutputStream());

                while(socket.isConnected()){
                    ClientCommand command = (ClientCommand) in.readObject();

                    command.execute(ClientModel.getInstance());
                }
            } catch(ClassCastException e){
                System.err.println("Class could not be cast to ClientCommand.");
                e.printStackTrace();
            } catch(ClassNotFoundException e) {
                System.err.println("Class could not be found.");
                e.printStackTrace();
            } catch(NotSerializableException e) {
                System.err.println("Class is not serializable.");
                e.printStackTrace();
            } catch (IOException e){
                System.err.println("IOException occurred. Message: " + e.getMessage());
                e.printStackTrace();
            } finally {
                out.close();
                out = null;
            }
        } catch(IOException e){
            System.err.println("Connection could not be established.");
            e.printStackTrace();
        }
    }
}
