/**
* @author Anthon Lenander, Erik Kieu, Phong Nguyen
* @version version 0.0.0
*/
public class CinemaBookingSystem {

	private static CinemaBookingSystem INSTANCE;
	
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
	
	public CinemaBookingSystem() {}
	
	public synchronized static CinemaBookingSystem getInstance(){
        if(INSTANCE == null){
            INSTANCE = new CinemaBookingSystem();
        }

        return INSTANCE;
    }
	
	// Serialize (saves in path: Working Tree-> Maven project -> Server) all collections
	
	public void updateAllCollections(){
		movieCollection.updateCollection();
		showCollection.updateCollection();
		customerCollection.updateCollection();
		bookingCollection.updateCollection();
	}
	
	public static void main (String [] args) {
		// Runs communication.
	    new Thread(SocketServerCommunication.getInstance()).start();

		//test get movies
		CinemaBookingSystem.getInstance().getMovieCollection().scanNewMovies();
	}

	public BookingCollection getBookingCollection(){
		return bookingCollection;
	}

	public MovieCollection getMovieCollection(){
		return movieCollection;
	}

	public ShowCollection getShowCollection(){
		return showCollection;
	}

	public CustomerCollection getCustomerCollection(){
		return customerCollection;
	}
	
}