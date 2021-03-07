package pane;

import app.ClientModel;
import util.IObserver;
import cinemaObjects.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import java.util.List;

/**
 * Pane for finding booking by inserting customer SSN or phone number.
 *
 * @author Jakob Ståhl, Konrad Rej, Erik Kieu
 * @version 2021-03-03
 */
public class FindBookingPane extends AbstractPane implements IObserver<ClientModel> {
    private final ClientModel cm;
    private List<Booking> bookings = new ArrayList<>();

    /**
     * Variables for finding a booking
     */
    private String ssn;
    private String phoneNumber;

    /**
     * Instantiates a new FindingBookingPane.
     *
     * @param frame the window frame
     */
    public FindBookingPane(JFrame frame) {
        super(frame);

        cm = ClientModel.getInstance();
        cm.addObserver(this);
        contentPane.setLayout(new BorderLayout());
        contentPane.add(enterInformation(), BorderLayout.NORTH);
        contentPane.add(showBookings(), BorderLayout.CENTER);
        contentPane.add(backToMain(), BorderLayout.SOUTH);
    }

    /**
     * Runs parents start function.
     */
    @Override
    public void start() {
        super.start();
    }

    private Container enterInformation() {

        Container enterInformation = new JPanel();
        enterInformation.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel l1 = new JLabel("SSN:");
        JTextField j1 = new JTextField(12);
        JLabel l2 = new JLabel("Phone number:");
        JTextField j2 = new JTextField(12);
        JButton b1 = new JButton("Search");

        b1.addActionListener((ActionEvent e) -> {
            if (!j1.getText().equals("")) {
                ssn = j1.getText();
                cm.updateBookingsBySSN(ssn);
            } else if (!j2.getText().equals("")) {
                phoneNumber = j2.getText();
                cm.updateBookingsByPhoneNumber(phoneNumber);
            }
        });

        enterInformation.add(l1);
        enterInformation.add(j1);
        enterInformation.add(l2);
        enterInformation.add(j2);
        enterInformation.add(b1);
        enterInformation.setPreferredSize(new Dimension(frame.getWidth(), 60));

        return enterInformation;
    }

    private Container showBookings() {
        Container showBookings = new JPanel();
        showBookings.setLayout(new GridLayout(1, 1, 10, 10));
        JLabel l1 = new JLabel("Bookings will show here", JLabel.CENTER);
        showBookings.add(l1);

        return showBookings;
    }

    private Container backToMain() {

        Container backToMain = new JPanel();
        backToMain.setLayout(new FlowLayout());
        JButton backButton = new JButton("Back");
        backToMain.add(backButton);
        backButton.addActionListener((ActionEvent e) ->
                cm.getNavigator().backToStart());
        backToMain.setPreferredSize(new Dimension(frame.getWidth(), 50));
        return backToMain;
    }

    private void updateShowBookings() {

        Container con = (Container) contentPane.getComponent(1);
        con.removeAll();

        if (bookings != null && bookings.size() > 0) {

            GridLayout grid;
            if(bookings.size()<4) {
                grid = new GridLayout(4, 1);
            }else
                grid = new GridLayout(bookings.size(), 1);

            JPanel framePanel = new JPanel();
            framePanel.setLayout(grid);

            for (Booking booking : bookings) {

                JPanel bookingPanel = new JPanel();
                bookingPanel.setLayout(new GridLayout(0,2,250,2));

                JLabel movieLabel = new JLabel("Movie: " + booking.getShow().getMovie().getTitle());
                bookingPanel.add(movieLabel);

                JLabel emptyLabel1 = new JLabel();
                bookingPanel.add(emptyLabel1);

                JLabel dateTimeLabel = new JLabel("When: " + booking.getShow().getShowDateAndTime());
                bookingPanel.add(dateTimeLabel);

                JLabel emptyLabel2 = new JLabel();
                bookingPanel.add(emptyLabel2);

                JLabel cinemaLabel = new JLabel("Where: " + booking.getShow().getCinema().getCinemaName());
                bookingPanel.add(cinemaLabel);

                JLabel emptyLabel3 = new JLabel();
                bookingPanel.add(emptyLabel3);

                JLabel theaterLabel = new JLabel("Theater nr: " + booking.getShow().getTheater().getTheaterNumber());
                bookingPanel.add(theaterLabel);

                JLabel emptyLabel4 = new JLabel();
                bookingPanel.add(emptyLabel4);

                JComboBox<String> seatsText = new JComboBox<>();
                for (Row row : booking.getBookedRows()) {
                    for (Seat seat : booking.getBookedSeats(row.getRowNumber())) {
                        seatsText.addItem("Row:" + row.getRowNumber() + " Seat:" + seat.getSeatNumber());
                    }
                }
                bookingPanel.add(seatsText);

                JButton cancelButton = new JButton("Cancel booking");
                cancelButton.addActionListener((ActionEvent e) -> {
                    int confirmation = JOptionPane.showConfirmDialog(framePanel
                            , "Are you sure you want to cancel your booking?"
                            ,"Confirm cancellation"
                            ,JOptionPane.YES_NO_OPTION);

                    if(confirmation == JOptionPane.YES_OPTION){
                        cm.cancelBooking(booking);
                        cm.getNavigator().startNewPane(new StatusPane(frame));
                    }
                });
                bookingPanel.add(cancelButton);
                bookingPanel.setBorder(new EtchedBorder(55,Color.LIGHT_GRAY, Color.WHITE));
                framePanel.add(bookingPanel);
            }
            con.add(framePanel);
        } else {
            JLabel label = new JLabel("No booking found");
            con.add(label);
        }
        contentPane.validate();
    }

    /**
     * Method to be called by observed object to notify about changes.
     * Retrieves new relevant values and updates the GUI accordingly.
     *
     * @param observable the observed object
     */
    @Override
    public void notify(ClientModel observable) {
        List<Booking> bookings = observable.getBookingCollection();

        if (bookings != null && bookings != this.bookings) {
            this.bookings = bookings;
            updateShowBookings();
        }
    }
}