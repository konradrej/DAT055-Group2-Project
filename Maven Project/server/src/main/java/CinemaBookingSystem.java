import cinemaObjects.*;
import collections.*;
import misc.ResponseStatus;
import server.ServerHandler;

import java.util.List;

public enum CinemaBookingSystem implements ServerHandler {
	INSTANCE();

	private final Cinema cinema;
	private BookingCollection bookingCollection = new BookingCollection("bookingCollection.txt");
	private MovieCollection movieCollection = new MovieCollection("movieCollection.txt");
	private ShowCollection showCollection = new ShowCollection("showCollection.txt");
	private CustomerCollection customerCollection = new CustomerCollection("customerCollection.txt");

	/**
	 * Constructor for initializing the CinemaBookingSystem instance
	 * Predefined for all Cinema, Theater, Row, Seat
	 */
	CinemaBookingSystem() {
		cinema = new Cinema("Underground Bio");
	}

	/**
	 * Get method to get the instance of CinemaBookingSystem object
	 *
	 * @return a CinemaBookingSystem of the instance
	 */
	public static CinemaBookingSystem getInstance(){
		return INSTANCE;
	}

	/**
	 * Method that serialize all collection of the object
	 */
	public void serializeAllCollection(){
		movieCollection.serializeCollection(movieCollection.getFilename());
		showCollection.serializeCollection(showCollection.getFilename());
		customerCollection.serializeCollection(customerCollection.getFilename());
		bookingCollection.serializeCollection(bookingCollection.getFilename());
	}

	/**
	 * Method that reads all the collection from a file to this objects collection
	 */
	public void readAllCollections() {
		this.movieCollection = movieCollection.readCollection();
		this.showCollection = showCollection.readCollection();
		this.customerCollection = customerCollection.readCollection();
		this.bookingCollection =  bookingCollection.readCollection();
	}

	public BookingCollection getBookingCollection(){
		return bookingCollection;
	}

	public MovieCollection getMovieCollection(){
		return this.movieCollection;
	}

	public ShowCollection getShowCollection() { return this.showCollection; }

	public ShowCollection getShowsByMovieDateRange(Movie movie, CinemaDate startDate, CinemaDate endDate) {
		return showCollection.getShowsGivenMovie(movie).getShowsGivenDateRange(startDate, endDate);
	}

	/**
	 * A method for getting the customerCollection instance of this CinemaBookingSystem
	 *
	 * @return	returns this objects instance variable customerCollection
	 */
	public CustomerCollection getCustomerCollection(){
		return customerCollection;
	}

	/**
	 * A method for getting the cinema of this CinemaBookingSystem
	 *
	 * @return	returns this objects instance variable cinema
	 */
	public Cinema getCinema() {
		return cinema;
	}


	/**
	 * A method for getting a customer given a SSN
	 *
	 * @param SSN	The social security number of a customer
	 * @return		Returns the customer inside of customerCollection that has SSN as its social security number
	 */
	public Customer getCustomerBySSN(String SSN)
	{
		return this.customerCollection.getCustomer(SSN);
	}

	/**
	 * A method for getting all the bookings of a specific customer given SSN
	 *
	 * @param SSN	The social security or phone number of the customer to look up
	 * @return 		Returns a collection of all the bookings by the customer given a SSN
	 */
	public List<Booking> getBookingsBySSN(String SSN)
	{
		return this.bookingCollection.getBookingsByCustomerSSN(SSN);
	}

	/**
	 * A method that should be called from a server command for creating a new booking
	 * @param show			The show of the new booking
	 * @param customer		The customer booking
	 * @param rows			A collection of all the rows, containing all seats to be reserved for the customer
	 * @return 				Returns a string to the client containing information about how the creation went
	 */
	public synchronized ResponseStatus createBooking(Show show, Customer customer, List<Row> rows) {
		this.bookingCollection.addBookings(show, customer, rows, this.showCollection);

		if (this.customerCollection.getCustomer(customer.getSSN()) == null) {
			this.customerCollection.addCustomer(customer);
		}
		return new ResponseStatus(true, "Booking successfully created");
	}

	/**
	 * A method that should be called from a server command for creating a new customer
	 * @param name			The name of the customer to be created
	 * @param phoneNumber	The phone number of the customer to be created
	 * @param SSN			The social security number of the customer to be created
	 * @return 				Returns a string to the client containing information about how the creation went
	 */
	public synchronized String createCustomer(String name, String phoneNumber, String SSN) {
		Customer newCustomer = null;

		try {
			newCustomer = new Customer(name, phoneNumber, SSN);
		} catch (IllegalArgumentException e) {
			System.err.println("Argument are of wrong type. Message: " + e.getMessage());
			e.printStackTrace();
			return "Creation of customer failed";
		} finally {
			this.customerCollection.addCustomer(newCustomer);
		}

		return "Customer successfully created";
	}

	/**
	 * This method cancels the input parameter bookings
	 *
	 * @param booking	cinemaObjects.Booking to be cancelled
	 */
	public synchronized void cancelBooking(Booking booking) {
		booking.cancelBooking();
		this.bookingCollection.removeBooking(booking);
	}

	@Override
	public List<Booking> getBookingsByPhoneNumber(String phoneNumber) {
		return this.bookingCollection.getBookingsByCustomerPhoneNumber(phoneNumber);
	}
}