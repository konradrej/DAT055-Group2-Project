import server.ServerHandler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public enum CinemaBookingSystem implements ServerHandler {

	INSTANCE();

	private final Cinema cinema;
	private BookingCollection bookingCollection = new BookingCollection("bookingCollection.txt");
	private MovieCollection movieCollection = new MovieCollection("movieCollection.txt");
	private ShowCollection showCollection = new ShowCollection("showCollection.txt");
	private CustomerCollection customerCollection = new CustomerCollection("customerCollection.txt");

	/**
	 * Constructor for initializing the CinemaBookingSystem instance
	 */
	CinemaBookingSystem() {

		Collection<Seat> seatTen = new LinkedList<>();
		for(int i = 0; i < 10; i++){
			seatTen.add(new Seat(i+1));
		}

		Collection <Seat> seatFifteen = new LinkedList<>();
		for(int i = 0; i < 15 ; i++){
			seatFifteen.add(new Seat(i+1));
		}

		Collection<Row> rowFive = new LinkedList <>();
		rowFive.add(new Row(1, seatTen));
		rowFive.add(new Row(2, seatTen));
		rowFive.add(new Row(3, seatFifteen));
		rowFive.add(new Row(4, seatFifteen));
		rowFive.add(new Row(5, seatFifteen));


		Collection <Row> rowThree = new LinkedList<>();
		rowThree.add(new Row(1, seatTen));
		rowThree.add(new Row(2, seatTen));
		rowThree.add(new Row(3, seatFifteen));

		Collection <Theater> theaterFive = new LinkedList<>();
		theaterFive.add(new Theater(1, rowFive));
		theaterFive.add(new Theater(2, rowThree));
		theaterFive.add(new Theater(3, rowThree));
		theaterFive.add(new Theater(4, rowFive));
		theaterFive.add(new Theater(5, rowFive));

		cinema = new Cinema("Underground Bio",theaterFive);

	}

	public static CinemaBookingSystem getInstance(){
		return INSTANCE;
	}



	// Serialize (saves in path: Working Tree-> Maven project -> Server) all collections

	public void updateAllCollections(){
		movieCollection.updateCollection(movieCollection.getFilename());
		showCollection.updateCollection(showCollection.getFilename());
		customerCollection.updateCollection(customerCollection.getFilename());
		bookingCollection.updateCollection(bookingCollection.getFilname());
	}

	public void readAllCollections(){

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
		catch (ClassCastException e){
			e.printStackTrace();
		}
		return new ShowCollection("empty");
	}

	public Collection<Seat> getAllSeatsByShow(Object show)
	{
		Collection<Seat> seatsByShow = new ArrayList<>();

		for(Show show1 : this.getShowCollection().getAllShows())
		{
			if(show1.equals(show))
			{
				seatsByShow.addAll(show1.getAllSeats());
			}
		}

		return seatsByShow;
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
	public Collection<Booking> getBookingsBySSN(String SSN)
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
	public String createBooking(Object show, Object customer, Collection<Object> rows)
	{
		Collection<Row> bookedRows = new ArrayList<>();

		try {
			for(Object r : rows)
			{
				bookedRows.add((Row)r);
			}
		} catch (ClassCastException e) {
			System.err.println("Class could not be casted. Message: " + e.getMessage());
			e.printStackTrace();
			return "Booking failed";
		} finally {
			this.bookingCollection.addBookings((Show)show, (Customer)customer, bookedRows);
		}

		return "Booking successfully created";
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
	 * @param booking	Booking to be cancelled
	 */
	public void cancelBooking(Booking booking) { booking.cancelBooking(); }
}