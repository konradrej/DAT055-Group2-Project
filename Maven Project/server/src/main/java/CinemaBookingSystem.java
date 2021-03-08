import cinemaObjects.*;
import collections.*;
import misc.ResponseStatus;
import server.ServerHandler;

import java.util.List;

/**
 * This singleton class contains all collections that is being modified
 * from each and acts like a "server".
 * It handles all incoming commands and replies back to the sender.
 *
 * @author Anthon Lenander
 * @version - 2021-03-07
 */

public enum CinemaBookingSystem implements ServerHandler {
    INSTANCE();

    private Cinema cinema = new Cinema("Underground Bio");
    private BookingCollection bookingCollection = new BookingCollection("bookingCollection.txt");
    private MovieCollection movieCollection = new MovieCollection("movieCollection.txt");
    private ShowCollection showCollection = new ShowCollection("showCollection.txt");
    private CustomerCollection customerCollection = new CustomerCollection("customerCollection.txt");

    /**
     * Get method to get the instance of CinemaBookingSystem object
     *
     * @return a CinemaBookingSystem of the instance
     */
    public static CinemaBookingSystem getInstance() {
        return INSTANCE;
    }

    /**
     * Method that serialize all collection of the object
     */
    public void serializeAllCollection() {
        movieCollection.serializeCollection(movieCollection.getFilename());
        showCollection.serializeCollection(showCollection.getFilename());
        customerCollection.serializeCollection(customerCollection.getFilename());
        bookingCollection.serializeCollection(bookingCollection.getFilename());
        cinema.serializeCinema(cinema.getCinemaName() + ".txt");
    }

    /**
     * Method that reads all the collection from a file to this objects collection
     */
    public void readAllCollections() {
        this.movieCollection = movieCollection.readCollection();
        this.showCollection = showCollection.readCollection();
        this.customerCollection = customerCollection.readCollection();
        this.bookingCollection = bookingCollection.readCollection();
        this.cinema = cinema.readCinema();
    }

    /**
     * Method for getting the collection of bookings from this object
     *
     * @return returns this objects collection of bookings
     */
    public BookingCollection getBookingCollection() {
        return bookingCollection;
    }

    /**
     * Method for getting the collection of movies from this object
     *
     * @return returns this objects collection of movies
     */
    public MovieCollection getMovieCollection() {
        return this.movieCollection;
    }

    /**
     * Method for getting the collection of shows from this object
     *
     * @return returns this objects collection of shows
     */
    public ShowCollection getShowCollection() {
        return this.showCollection;
    }

    /**
     * Method for getting the collection of shows from this object given a movie and a date range
     *
     * @return returns this objects collection of shows given a movie and a date range
     */
    public ShowCollection getShowsByMovieDateRange(Movie movie, CinemaDate startDate, CinemaDate endDate) {
        return showCollection.getShowsGivenMovie(movie).getShowsGivenDateRange(startDate, endDate);
    }

    /**
     * A method for getting the customerCollection instance of this CinemaBookingSystem
     *
     * @return returns this objects instance variable customerCollection
     */
    public CustomerCollection getCustomerCollection() {
        return customerCollection;
    }

    /**
     * A method for getting the cinema of this CinemaBookingSystem
     *
     * @return returns this objects instance variable cinema
     */
    public Cinema getCinema() {
        return cinema;
    }

    /**
     * A method for getting a customer given a SSN
     *
     * @param SSN The social security number of a customer
     * @return Returns the customer inside of customerCollection that has SSN as its social security number
     */
    public Customer getCustomerBySSN(String SSN) {
        return this.customerCollection.getCustomer(SSN);
    }

    /**
     * A method for getting all the bookings of a specific customer given SSN
     *
     * @param SSN The social security or phone number of the customer to look up
     * @return Returns a collection of all the bookings by the customer given a SSN
     */
    public List<Booking> getBookingsBySSN(String SSN) {
        return this.bookingCollection.getBookingsByCustomerSSN(SSN);
    }

    /**
     * A method that should be called from a server command for creating a new booking
     *
     * @param show     The show of the new booking
     * @param customer The customer booking
     * @param rows     A collection of all the rows, containing all seats to be reserved for the customer
     * @return Returns a string to the client containing information about how the creation went
     */
    public synchronized ResponseStatus createBooking(Show show, Customer customer, List<Row> rows) {
        this.bookingCollection.addBookings(show, customer, rows, this.showCollection);

        if (this.customerCollection.getCustomer(customer.getSSN()) == null) {
            this.customerCollection.addCustomer(customer);
        }
        return new ResponseStatus(true, "Booking successfully created");
    }

    /**
     * This method cancels the input parameter bookings
     *
     * @param booking cinemaObjects.Booking to be cancelled
     */
    public synchronized ResponseStatus cancelBooking(Booking booking) {
        ResponseStatus response;
        boolean removed = this.bookingCollection.removeBooking(booking, this.showCollection);

        if (removed) response = new ResponseStatus(true, "Booking successfully removed");
        else response = new ResponseStatus(false, "Booking failed to get removed");

        return response;
    }

    /**
     * Method for getting a customers bookings given a phone number
     *
     * @param phoneNumber the customers phone number
     * @return returns a list of bookings
     */
    @Override
    public List<Booking> getBookingsByPhoneNumber(String phoneNumber) {
        return this.bookingCollection.getBookingsByCustomerPhoneNumber(phoneNumber);
    }
}