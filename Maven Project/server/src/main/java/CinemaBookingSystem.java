/**
* @author Anthon Lenander, Erik Kieu, Phong Nguyen
* @version version 0.0.0
*/
public enum CinemaBookingSystem {

	INSTANCE();
	
	/**
	 * Constructor for initializing the CinemaBookingSystem instance
	 * 
	 * @param bookingCollection 	a collection of bookings
	 * @param customerCollection	a collection of customers
	 * @param showCollection		a collection of shows
	 * @param movieCollection		a collection of movies
	 */
	private final BookingCollection bookingCollection = new BookingCollection("bookingCollection.txt");
	private final MovieCollection movieCollection = new MovieCollection("movieCollection.txt");
	private final ShowCollection showCollection = new ShowCollection("showCollection.txt");
	private final CustomerCollection customerCollection = new CustomerCollection("customerCollection.txt");
	
	CinemaBookingSystem() { //DO ANY NECESSARY INITIALIZATION }
	
	public static CinemaBookingSystem getInstance(){
        return INSTANCE;
    }
	
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
	
	public static void main (String [] args) {
		// Runs communication.
	    new Thread(SocketServerCommunication.getInstance()).start();

		//test get movies
		CinemaBookingSystem.getInstance().getMovieCollection().scanNewMovies();
	}
}