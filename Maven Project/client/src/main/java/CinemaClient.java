import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class CinemaClient {
    private JFrame frame;

    public static void main(String[] args) {
        Map<String, String> ipNum = ipReader.readText();

        SocketClientCommunication scc = SocketClientCommunication.getInstance();
        scc.setIp(ipNum.getOrDefault("ip", "localhost"));

        new Thread(scc).start();

        new CinemaClient();
    }

    public CinemaClient() { createFrame(); }

    private void createFrame(){
        this.frame = new JFrame("Cinema");
        this.frame.setPreferredSize(new Dimension(600, 600));
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ClientModel.getInstance().setNavigator(new Navigator(new MainMenuPane(frame)));

        //new MainMenuPane(frame).start();

        this.frame.pack();
        this.frame.setVisible(true);
    }
}
