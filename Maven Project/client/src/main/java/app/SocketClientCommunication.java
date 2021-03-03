package app;

import client.ClientCommand;
import server.ServerCommand;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;

/**
 * Singleton class that handles establishing a connection to the server
 * as well as the execution of received commands from server and sending
 * commands to server.
 *
 * @author Konrad Rej
 * @version 2021-03-03
 */
public enum SocketClientCommunication implements Runnable {
    INSTANCE;

    /**
     * Get singleton instance of this class
     *
     * @return app.SocketClientCommunication instance
     */
    public static SocketClientCommunication getInstance() {
        return INSTANCE;
    }

    private String ip = "localhost";
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Socket socket;

    /**
     * Sets the ip address value to be used for socket from parameter ip. If
     * the socket is not active yet it will write to System.out the ip to be
     * used to open the socket, if the socket is already active it will instead
     * write to System.err and inform about socket being active and which ip is
     * already in use instead.
     *
     * @param ip the new ip address
     */
    public void setIp(String ip) {
        if (this.socket == null) {
            this.ip = ip;

            System.out.println("IP set to: " + this.ip);
        } else {
            System.err.println("IP could not be set to: " + ip + ", already using: " + this.ip);
        }
    }

    /**
     * Sends a given ServerCommand through the socket to the server.
     *
     * @param command the given command to send to server
     */
    public void sendCommand(ServerCommand command) {
        try {
            this.out.writeObject(command);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Establishes a socket and if successful gets input and output streams
     * from it and then waits for commands to be received on the input stream
     * after which it will run the execute function and continue to listen
     * for commands.
     */
    @Override
    public void run() {
        try {
            this.socket = new Socket(ip, 888);
            System.out.println("Connection established.");
            JOptionPane.showMessageDialog(null, "Connection to server established.", "Connection success", JOptionPane.INFORMATION_MESSAGE);
            ClientModel.getInstance().setConnectionAlive(true);
        } catch (IOException e) {
            System.err.println("Connection could not be established.");
            JOptionPane.showMessageDialog(null, "Connection to server could not be established.\nPress OK to exit.", "Connection error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            while (socket.isConnected()) {
                ClientCommand command = (ClientCommand) in.readObject();
                command.execute(ClientModel.getInstance());
            }
        } catch (EOFException e) {
            closeConnection();
        } catch (SocketException e) {
            System.out.println("Connection reset.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            ClientModel.getInstance().setConnectionAlive(false);
            JOptionPane.showMessageDialog(null, "There was a problem with server communication.\nPress OK to exit.", "Communication error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    /**
     * Flushes the out buffer before closing all relevant streams
     * and socket.
     */
    public void closeConnection() {
        try {
            if (!socket.isClosed()) {
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