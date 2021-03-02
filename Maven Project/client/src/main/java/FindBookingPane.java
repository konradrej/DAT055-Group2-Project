import ObserverPattern.IObserver;
import cinemaObjects.*;
import collections.CustomerCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import java.util.List;

public class FindBookingPane extends AbstractPane implements IObserver<ClientModel> {

    private String ssn;
    private String phonenumber;
    private ClientModel cm;
    private List<Booking> bookings = new ArrayList<>();

    public FindBookingPane(JFrame frame){
        super(frame);

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
           // if(j1.getText() == ((Customer)customer.))


            if(j1.getText()!=null) {
                ssn = j1.getText();
                cm.updateBookingsBySSN(ssn);
            } else if(j2.getText()!=null){
                phonenumber = j2.getText();
                cm.updateBookingsByPhoneNumber(phonenumber);
            }
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
        Container con = (Container) contentPane.getComponent(1);
        con.setLayout(new GridLayout());
        con.removeAll();

        // TEMP
        Seat newSeat = new Seat(1);
        ArrayList<Seat> seats = new ArrayList<>();
        seats.add(newSeat);
        Row newRow = new Row(1, seats);
        ArrayList<Row> rows = new ArrayList<>();
        rows.add(newRow);
        Show show = cm.getShowCollection().getAllShows().iterator().next();
        Booking newBooking = new Booking(show, new Customer("Konrad", "999999", "99"), rows);
        bookings.add(newBooking);
        Booking newBooking1 = new Booking(show, new Customer("Konrad", "999999", "99"), rows);
        bookings.add(newBooking1);
        Booking newBooking2 = new Booking(show, new Customer("Konrad", "999999", "99"), rows);
        bookings.add(newBooking2);
        Booking newBooking3 = new Booking(show, new Customer("Konrad", "999999", "99"), rows);
        bookings.add(newBooking3);
        Booking newBooking4 = new Booking(show, new Customer("Konrad", "999999", "99"), rows);
        bookings.add(newBooking4);
        // END TEMP

        if (bookings != null && bookings.size() > 0) {
            JPanel wrapperpanel = new JPanel();
            JScrollPane scrollpane = new JScrollPane(wrapperpanel);
            scrollpane.setLayout(new ScrollPaneLayout());

            wrapperpanel.setLayout(new BoxLayout(wrapperpanel, BoxLayout.Y_AXIS));

            for(Booking booking : bookings) {
                JPanel bookingPanel = new JPanel();
                bookingPanel.setLayout(new BorderLayout());
                bookingPanel.setBorder(BorderFactory.createEtchedBorder());

                JPanel buttonContainer = new JPanel();
                JPanel infoContainer = new JPanel();
                infoContainer.setLayout(new BoxLayout(infoContainer, BoxLayout.Y_AXIS));

                JLabel movieLabel = new JLabel("Movie: " + booking.getShow().getMovie().getTitle() );
                movieLabel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
                infoContainer.add(movieLabel);

                JLabel dateTimeLabel = new JLabel("Date and time: " + booking.getShow().getShowDateAndTime() );
                dateTimeLabel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
                infoContainer.add(dateTimeLabel);

                JLabel cinemaLabel = new JLabel("Cinema " + booking.getShow().getCinema().getCinemaName());
                cinemaLabel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
                infoContainer.add(cinemaLabel);

                JLabel theaterLabel = new JLabel("Theater: " + booking.getShow().getTheater().getTheaterNumber());
                theaterLabel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
                infoContainer.add(theaterLabel);

                JPanel rowSeatPanel = new JPanel();
                rowSeatPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
                JLabel bookedSeatsLabel = new JLabel("Booked Seats: ");
                JComboBox<String> seatsText = new JComboBox<>();
                for(Row row: booking.getBookedRows()){
                    for(Seat seat : booking.getBookedSeats(row.getRowNumber())){
                        seatsText.addItem("Row:" + row.getRowNumber() + " Seat:" + seat.getSeatNumber());
                    }
                }

                rowSeatPanel.add(bookedSeatsLabel);
                rowSeatPanel.add(seatsText);
                rowSeatPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);

                JButton cancelButton = new JButton("Cancel booking");
                buttonContainer.add(cancelButton);

                infoContainer.add(rowSeatPanel);

                buttonContainer.setBackground(Color.BLACK);
                infoContainer.setBackground(Color.RED);
                rowSeatPanel.setBackground(Color.yellow);

                bookingPanel.add(buttonContainer, BorderLayout.EAST);
                bookingPanel.add(infoContainer, BorderLayout.CENTER);
                bookingPanel.setPreferredSize(new Dimension(scrollpane.getWidth(), 120));
                wrapperpanel.add(bookingPanel);
            }

            con.add(scrollpane);
            con.setLayout(new GridLayout());
        }else{
            JLabel label = new JLabel("No booking found");
            con.add(label);
        }

        contentPane.validate();
    }

    public Container backToMain(){

        Container backToMain = new JPanel();
        backToMain.setLayout(new FlowLayout());
        JButton backButton = new JButton("Back");
        backToMain.add(backButton);
        backButton.addActionListener((ActionEvent e) ->
                cm.getNavigator().backToStart());
        backToMain.setPreferredSize(new Dimension(frame.getWidth(), 50));
        return backToMain;
    }

    @Override
    public void notify(ClientModel observable) {
        List<Booking> bookings = observable.getBookingCollection();

        if(bookings != null && bookings != this.bookings){
            this.bookings = bookings;
            updateShowBookings();
        }

    }
}
