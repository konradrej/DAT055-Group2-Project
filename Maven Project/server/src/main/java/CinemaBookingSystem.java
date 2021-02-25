import cinemaObjects.*;
import collections.*;
import client.ResponseStatus;
import server.ServerHandler;
import collections.ShowCollection;

import java.io.IOException;
import java.util.ArrayList;
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

		List<Seat> seatTen = new ArrayList<>();
		for(int i = 0; i < 10; i++){
			seatTen.add(new Seat(i+1));
		}

		List <Seat> seatFifteen = new ArrayList<>();
		for(int i = 0; i < 15 ; i++){
			seatFifteen.add(new Seat(i+1));
		}

		List<Row> rowFive = new ArrayList <>();
		rowFive.add(new Row(1, seatTen));
		rowFive.add(new Row(2, seatTen));
		rowFive.add(new Row(3, seatFifteen));
		rowFive.add(new Row(4, seatFifteen));
		rowFive.add(new Row(5, seatFifteen));


		List <Row> rowThree = new ArrayList<>();
		rowThree.add(new Row(1, seatTen));
		rowThree.add(new Row(2, seatTen));
		rowThree.add(new Row(3, seatFifteen));

		List <Theater> theaterFive = new ArrayList<>();
		theaterFive.add(new Theater(1, rowFive));
		theaterFive.add(new Theater(2, rowThree));
		theaterFive.add(new Theater(3, rowThree));
		theaterFive.add(new Theater(4, rowFive));
		theaterFive.add(new Theater(5, rowFive));

		cinema = new Cinema("Underground Bio",theaterFive);

	}

	/**
	 * Get method to get the instance of CinemaBookingSystem objecy
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
		bookingCollection.serializeCollection(bookingCollection.getFilname());
	}

	/**
	 * Method that reads all the collection from a file to this objects collection
	 */
	public void readAllCollections() throws IOException {
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

	public ShowCollection getShowsByMovie(Object movie)
	{
		try {
			Movie m = (Movie) movie;
			return showCollection.getShowsGivenMovie(m);
		}
		catch (ClassCastException e) {
			e.printStackTrace();
		}
		return new ShowCollection("Empty showCollection");
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
	 * @param SSN	The social security of the customer to look up
	 * @return 		Returns a collection of all the bookings by the customer given a SSN
	 */
	public List<Booking> getBookingsBySSN(String SSN)
	{
		Customer customer = getCustomerBySSN(SSN);

		return this.bookingCollection.getBookingsByCustomer(customer);
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
	 * A method that should be called from a server command for creating a new booking
	 * @param show			The show of the new booking
	 * @param customer		The customer booking
	 * @param rows			A collection of all the rows, containing all seats to be reserved for the customer
	 * @return 				Returns a string to the client containing information about how the creation went
	 */
	public ResponseStatus createBooking(Object show, Object customer, ArrayList<Object> rows)
	{
		List<Row> bookedRows = new ArrayList<>();

		try {
			for(Object r : rows)
			{
				bookedRows.add((Row)r);
			}

			this.bookingCollection.addBookings((Show)show, (Customer)customer, bookedRows);

			if (this.customerCollection.getCustomer((((Customer) customer).getSSN())) != null)
			{
				this.customerCollection.addCustomer((Customer)customer);
			}

		} catch (ClassCastException e) {
			System.err.println("Class could not be casted. Message: " + e.getMessage());
			e.printStackTrace();
			return new ResponseStatus(false, "Booking failed");
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
	public String createCustomer(String name, String phoneNumber, String SSN)
	{
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
	public void cancelBooking(Object booking)
	{
		((Booking)booking).cancelBooking();
		this.bookingCollection.removeBooking((Booking) booking);
	}
}