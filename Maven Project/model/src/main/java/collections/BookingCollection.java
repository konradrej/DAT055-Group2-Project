package collections;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import cinemaObjects.*;

/**
 * This class handles the collection of bookings,
 * that may modify and provide different bookings
 *
 * @author Phong Nguyen
 * @version 2021-03-07
 */

public class BookingCollection extends AbstractCollection {

    private static final long serialVersionUID = 1450784786789696365L;
    private final List<Booking> allBookings;
    private final String filename;

    /**
     * Constructor for initializing the BookingCollection instance
     *
     * @param filename Filename of the object when serializing
     */
    public BookingCollection(String filename) {
        this.filename = filename;
        allBookings = new ArrayList<>();
    }

    /**
     * Get all bookings given a customer PhoneNumber
     *
     * @param SSN - the SSN of the customer
     * @return a list of bookings of the customer
     */
    public List<Booking> getBookingsByCustomerSSN(String SSN) {
        List<Booking> bookings = new ArrayList<>();
        for (Booking b : this.allBookings) {
            Customer c = b.getCustomer();
            if (c.getSSN().equals(SSN)) {
                bookings.add(b);
            }
        }
        return bookings;
    }

    /**
     * Get all bookings given a customer SSN
     *
     * @param PhoneNumber - the PhoneNumber of the customer
     * @return a list of bookings of the customer
     */

    public List<Booking> getBookingsByCustomerPhoneNumber(String PhoneNumber) {
        List<Booking> bookings = new ArrayList<>();
        for (Booking b : this.allBookings) {
            Customer c = b.getCustomer();
            if (c.getPhoneNumber().equals(PhoneNumber)) {
                bookings.add(b);
            }
        }
        return bookings;
    }

    /**
     * Adds booking to the objects booking collection given show,
     * customer, list of rows containing seats
     *
     * @param s              - The show of the booking
     * @param c              - The customer of the booking
     * @param r              - The list of the row booked containing seats
     * @param showCollection - showCollection to update
     */
    public void addBookings(Show s, Customer c, List<Row> r, ShowCollection showCollection) {
        Booking b = new Booking(s, c, r);
        this.allBookings.add(b);
        for (Row row1 : r) {
            for (Row row : s.getTheater().getAllRows()) {
                if (row.getRowNumber() == row1.getRowNumber()) {
                    for (Seat seat : row.getAllSeats()) {
                        for (Seat seat1 : row1.getAllSeats()) {
                            if (seat.getSeatNumber() == seat1.getSeatNumber()) {
                                seat.updateSeatStatus(false);
                            }
                        }
                    }
                }
            }
        }
        showCollection.updateShow(s, s.getMovie(), s.getShowDateAndTime(), s.getCinema(), s.getTheater(), s.getUniqueID());

    }

    /**
     * Removes a booking from the objects booking list
     *
     * @param b              - the booking that removes
     * @param showCollection - the ShowCollection that is being updated
     * @return - true if the removal of the booking was successful, false if not
     */
    public boolean removeBooking(Booking b, ShowCollection showCollection) {
        for (Booking b2 : this.allBookings) {
            if (b2.getUniqueID().equals(b.getUniqueID())) {
                for (Show s : showCollection.getAllShows()) {
                    if (b.getShow().getUniqueID().equals(s.getUniqueID())) {
                        for (Row row1 : b.getBookedRows()) {
                            for (Row row : s.getTheater().getAllRows()) {
                                if (row.getRowNumber() == row1.getRowNumber()) {
                                    for (Seat seat : row.getAllSeats()) {
                                        for (Seat seat1 : row1.getAllSeats()) {
                                            if (seat.getSeatNumber() == seat1.getSeatNumber()) {
                                                seat.updateSeatStatus(true);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        showCollection.updateShow(s, s.getMovie(), s.getShowDateAndTime(), s.getCinema(), s.getTheater(), s.getUniqueID());
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Get method for the filename
     *
     * @return the filename when serializing
     */

    public String getFilename() {
        return this.filename;
    }

    /**
     * Get method for the list of bookings
     *
     * @return a list of customers
     */

    public List<Booking> getAllBookings() {
        return this.allBookings;
    }

    /**
     * Read serialized file from the filenames path
     *
     * @return BookingCollection of the read file
     */

    public BookingCollection readCollection() {
        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(this.filename))) {

            BookingCollection readThis = (BookingCollection) stream.readObject();
            System.out.println("File: " + this.filename + " has been read");
            return readThis;
        } catch (ClassCastException | ClassNotFoundException | NullPointerException | IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return new BookingCollection(this.filename);
        }
    }
}
