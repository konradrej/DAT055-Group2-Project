import ObserverPattern.IObserver;
import cinemaObjects.Booking;
import server.GetBookingsByPhoneNumberCommand;
import server.GetBookingsBySSNCommand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.print.Book;
import java.util.*;
import java.util.List;

public class FindBookingPane extends AbstractPane implements IObserver<ClientModel> {

    private String ssn;
    private String phonenumber;
    private ClientModel cm;
    private Collection<Booking> bookings;

    public FindBookingPane(JFrame frame){
        super(frame);
    }

    @Override
    public void init(){

        cm = ClientModel.getInstance();
        cm.addObserver(this);
        contentPane.setLayout(new BorderLayout());
        contentPane.add(enterInformation(), BorderLayout.NORTH);
        contentPane.add(showBookings(), BorderLayout.CENTER);
        contentPane.add(backToMain(), BorderLayout.SOUTH);
    }

    @Override
    public void start(){
        super.start();
    }

    public Container enterInformation() {

        Container enterInformation = new JPanel();
        enterInformation.setLayout(new FlowLayout());

        JLabel l1 = new JLabel("Enter ssn:");
        JTextField j1 = new JTextField(13);
        JLabel l2 = new JLabel("Phone:");
        JTextField j2 = new JTextField(13);
        JButton b1 = new JButton("Show Bookings");

        b1.addActionListener((ActionEvent e) -> {
            if(j1.getText()!=null) {
                ssn = j1.getText();
                SocketClientCommunication.getInstance().sendCommand(new GetBookingsBySSNCommand(ssn));
            } else if(j2.getText()!=null){
                phonenumber = j2.getText();
                SocketClientCommunication.getInstance().sendCommand(new GetBookingsByPhoneNumberCommand(phonenumber));
            }
            //System.out.println(phonenumber + " " + ssn);
        });

        enterInformation.add(l1);
        enterInformation.add(j1);
        enterInformation.add(l2);
        enterInformation.add(j2);
        enterInformation.add(b1);
        enterInformation.setPreferredSize(new Dimension(frame.getWidth(), 50));

        return enterInformation;

    }

    public Container showBookings() {
        Container showBookings = new JPanel();
        showBookings.setLayout(new GridLayout(4, 1, 10,10));
        JLabel l1 = new JLabel("Bookings will show here", JLabel.CENTER);
        showBookings.add(l1);
        return showBookings;
    }
    public void updateShowBookings() {
        if (bookings != null) {
            Container con = (Container) contentPane.getComponent(1);
            con.removeAll();

            JPanel wrapperpanel = new JPanel();
            JScrollPane scrollpane = new JScrollPane();

            for(Booking booking : bookings) {

            }
            con.add(scrollpane);
            scrollpane.add(wrapperpanel);

            JLabel bookinglabel = new JLabel();
            wrapperpanel.add(bookinglabel);
        }
    }

    public Container backToMain(){

        Container backToMain = new JPanel();
        backToMain.setLayout(new FlowLayout());
        JButton backButton = new JButton("Back");
        backToMain.add(backButton);
        backButton.addActionListener((ActionEvent e) -> {
            stop();
        });
        backToMain.setPreferredSize(new Dimension(frame.getWidth(), 50));
        return backToMain;
    }

    @Override
    public void notify(ClientModel observable) {
        Collection<Booking> bookings = observable.getBookingCollection();

        if(bookings != null && bookings != this.bookings){
            this.bookings = bookings;
            updateShowBookings();
        }
        System.out.println("h");

    }
}
