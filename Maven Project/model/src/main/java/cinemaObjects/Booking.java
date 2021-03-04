package cinemaObjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * This class handles the booking for a customer
 * and stores a show, customer information, row
 * and seat number for all booked seats.
 *
 * @author Erik Kieu
 * @version 2021-03-02
 */
public class Booking implements Serializable {

    private static final long serialVersionUID = 408224943807153172L;
    private Show show;
    private Customer customer;
    private List<Row> bookedRows;
    private final String uniqueID;

    /**
     * Constructor for initializing the cinemaObjects.Booking instance
     *
     * @param show     what show is booked
     * @param customer which customer the booking belongs to
     * @param rows     what rows the customer has booked
     */
    public Booking(Show show, Customer customer, List<Row> rows) {
        this.show = show;
        this.customer = customer;
        this.bookedRows = rows;
        this.uniqueID = UUID.randomUUID().toString();
    }

    /**
     * Method for getting what rows the customer has booked seats in
     *
     * @return rows        what rows the customer has booked seats in
     */
    public List<Row> getBookedRows() {
        return this.bookedRows;
    }

    /**
     * Method for getting the seats in row with rowNumber that the customer has booked
     *
     * @param rowNumber what row the seats are located in
     * @return rows            what seats the customer has booked
     */
    public List<Seat> getBookedSeats(int rowNumber) {
        List<Seat> bookedSeats = new ArrayList<>();

        for (Row r : this.bookedRows) {
            if (r.getRowNumber() == rowNumber) {
                bookedSeats = r.getAllSeats();
            }
        }

        return bookedSeats;
    }

    /**
     * Method for internally changing the status of the booking
     */
    public void cancelBooking() {
        for (Row r : bookedRows) {
            for (Seat s : r.getAllSeats()) {
                s.updateSeatStatus(false);
            }
        }
    }

    /**
     * Method for getting what customer the booking belongs to
     *
     * @return customer        what customer the booking belongs to
     */
    public Customer getCustomer() {
        return this.customer;
    }

    /**
     * Method for getting the show instance of this booking
     *
     * @return returns the show instance of this booking
     */
    public Show getShow() {
        return this.show;
    }

    public String getUniqueID(){
        return this.uniqueID;
    }
}