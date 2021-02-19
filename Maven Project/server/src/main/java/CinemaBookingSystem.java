import java.util.*;

/**
* @author Anthon Lenander, Erik Kieu, Phong Nguyen
* @version version 0.0.0
*/
public enum CinemaBookingSystem {

	INSTANCE();

	private final BookingCollection bookingCollection = new BookingCollection("bookingCollection.txt");
	private final MovieCollection movieCollection = new MovieCollection("movieCollection.txt");
	private final ShowCollection showCollection = new ShowCollection("showCollection.txt");
	private final CustomerCollection customerCollection = new CustomerCollection("customerCollection.txt");

	/**
	 * Constructor for initializing the CinemaBookingSystem instance
	 */
	CinemaBookingSystem() { /*DO ANY NECESSARY INITIALIZATION*/ }

	public static CinemaBookingSystem getInstance() { return INSTANCE; }
	
	// Serialize (saves in path: Working Tree-> Maven project -> Server) all collections
	
	//Should we have synchronized here? Applies to all of the following methods
	public synchronized void updateAllCollections(){
		movieCollection.updateCollection();
		showCollection.updateCollection();
		customerCollection.updateCollection();
		bookingCollection.updateCollection();
	}
	
	public synchronized BookingCollection getBookingCollection(){
		return bookingCollection;
	}

	public synchronized MovieCollection getMovieCollection(){
		return movieCollection;
	}

	public synchronized ShowCollection getShowCollection(){
		return showCollection;
	}

	public synchronized CustomerCollection getCustomerCollection(){
		return customerCollection;
	}
	
	public synchronized Show getShowByMovie(Movie movie)
	{
		for(Show s : this.showCollection.getAllShows())
		{
			if(s.getMovie() == movie)
			{
				return s;
			}
		}

		return null;
	}

	public synchronized Collection<Seat> getAllSeatsByShow(Show show)
	{
		for(Show s : this.showCollection.getAllShows())
		{
			if(show.equals(s)) return s.getAllSeats();
		}

		return null;
	}

	public synchronized Customer getCustomerBySSN(String SSN)
	{
		return this.customerCollection.getCustomer(SSN);
	}

	//Discuss return type of this method
	public synchronized Customer createCustomer(String name, String phoneNumber, String SSN)
	{
		Customer newCustomer = new Customer(name, phoneNumber, SSN);
		this.customerCollection.addCustomer(newCustomer);

		return newCustomer;
	}

	//Discuss return type of this method
	public synchronized Booking createBooking(Show show, Customer customer, Collection<Row> rows)
	{
		Booking newBooking = new Booking(show, customer, rows);
		this.bookingCollection.addBookings(show, customer, rows);

		return newBooking;
	}

	public synchronized Collection<Booking> findBookingsBySSN(String SSN)
	{
		Customer customerToFind = this.customerCollection.getCustomer(SSN);

		if(customerToFind != null)
		{
			return this.bookingCollection.getBookingsByCustomer(customerToFind);
		}

		return null;
	}
	
	public static void main (String [] args) {
		// Runs communication.
	    new Thread(SocketServerCommunication.getInstance()).start();

		//test get movies
		CinemaBookingSystem.getInstance().getMovieCollection().scanNewMovies();
	}
}