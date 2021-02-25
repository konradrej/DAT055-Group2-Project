import ObserverPattern.IObserver;
import cinemaObjects.Booking;
import cinemaObjects.Customer;
import cinemaObjects.Row;
import cinemaObjects.Seat;
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
    private Collection<Booking> bookings = new ArrayList<>();

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

        Container con = (Container) contentPane.getComponent(1);
        con.setLayout(new GridLayout());
        con.removeAll();


        // TEMP
        Seat newSeat = new Seat(1);
        Collection<Seat> seats = new ArrayList<>();
        seats.add(newSeat);
        Row newRow = new Row(1, seats);
        Collection<Row> rows = new ArrayList<>();
        rows.add(newRow);
        Booking newBooking = new Booking(cm.getShowCollection().getAllShows().iterator().next(), new Customer("Konrad", "999999", "99"), rows);
        bookings.add(newBooking);
        // END TEMP

        if (bookings != null && bookings.size() > 0) {
            JPanel wrapperpanel = new JPanel();
            JScrollPane scrollpane = new JScrollPane(wrapperpanel);
            scrollpane.setPreferredSize(new Dimension(200, 200));
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
                        seatsText.addItem("R:" + row.getRowNumber() + " S:" + seat.getSeatNumber());
                    }
                }
                rowSeatPanel.add(bookedSeatsLabel);
                rowSeatPanel.add(seatsText);

                infoContainer.add(rowSeatPanel);


                buttonContainer.setBackground(Color.BLACK);
                infoContainer.setBackground(Color.RED);
                rowSeatPanel.setBackground(Color.yellow);

                bookingPanel.add(buttonContainer,BorderLayout.EAST);
                bookingPanel.add(infoContainer, BorderLayout.CENTER);
                bookingPanel.setPreferredSize(new Dimension(scrollpane.getWidth(), 200));
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

    }
}
