package app;

import pane.MainMenuPane;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * Class for setting up the GUI for client.
 *
 * @author Jakob Ståhl
 * @version 2021-03-08
 */
public class CinemaClient {

    /**
     * Main method on client side.
     *
     * @param args The command line arguments.
     **/
    public static void main(String[] args) {
        Map<String, String> ipNum = ConfigReader.readText();

        SocketClientCommunication scc = SocketClientCommunication.getInstance();
        scc.setIp(ipNum.getOrDefault("ip", "localhost"));

        new Thread(scc).start();

        createFrame();
    }

    private static void createFrame() {
        JFrame frame = new JFrame("Cinema");
        frame.setPreferredSize(new Dimension(600, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ClientModel.getInstance().setNavigator(new Navigator(new MainMenuPane(frame)));

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}