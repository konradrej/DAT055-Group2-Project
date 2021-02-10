import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CinemaClient {
    private JFrame frame;

    public static void main(String[] args){
        new CinemaClient();
    }

    public CinemaClient(){
        createFrame();
    }

    private void createFrame(){
        this.frame = new JFrame("Cinema");
        this.frame.setPreferredSize(new Dimension(600, 600));
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final Container contentPane = this.frame.getContentPane();
        contentPane.setLayout(new GridLayout(4, 4, 10, 10));

        JButton bookingButton = new JButton("Book");
        JButton exitButton = new JButton("Exit");

        ShowSelectionPane booking = new ShowSelectionPane(this.frame);


        bookingButton.addActionListener((ActionEvent e) -> booking.start());

        exitButton.addActionListener((ActionEvent e) -> System.exit(0));

        contentPane.add(bookingButton);
        contentPane.add(exitButton);

        this.frame.pack();
        this.frame.setVisible(true);
    }
}
