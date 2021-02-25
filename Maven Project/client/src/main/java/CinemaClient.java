import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
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

    public CinemaClient(){
        createFrame();
    }

    private void createFrame(){
        this.frame = new JFrame("cinemaObjects.Cinema");
        this.frame.setPreferredSize(new Dimension(600, 600));
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final Container contentPane = this.frame.getContentPane();
        contentPane.setLayout(new GridLayout(4, 4, 10, 10));

        JButton bookingButton = new JButton("Book");
        JButton exitButton = new JButton("Exit");

        BookingPane booking = new BookingPane(this.frame);

        bookingButton.addActionListener((ActionEvent e) -> new BookingPane(frame).start());

        exitButton.addActionListener((ActionEvent e) -> System.exit(0));

        contentPane.add(bookingButton);
        contentPane.add(exitButton);

        this.frame.pack();
        this.frame.setVisible(true);
    }
}
