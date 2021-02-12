package src;
import java.io.*;

/**
* @author Anthon Lenander, Erik Kieu, Phong Nguyen
* @version version 0.0.0
*/
public class CinemaBookingSystem {

	/**
	 * Constructor for initializing the CinemaBookingSystem instance
	 * 
	 * @param bookingCollection 	a collection of bookings
	 * @param customerCollection	a collection of customers
	 * @param showCollection		a collection of shows
	 * @param movieCollection		a collection of movies
	 */
	
	
	private BookingCollection bookingCollection;
	private MovieCollection movieCollection;
	private ShowCollection showCollection;
	private CustomerCollection customerCollection;
	
	public CinemaBookingSystem(BookingCollection bookingCollection, CustomerCollection customerCollection, ShowCollection showCollection, 
	MovieCollection movieCollection)
	{
		this.bookingCollection = bookingCollection;
		this.customerCollection = customerCollection;
		this.movieCollection = movieCollection;
		this.showCollection = showCollection;
	}
	
	
	// Serialize (saves in path: Working Tree-> Server -> src) all collection
	
	public void updateAllCollections(){
		movieCollection.updateCollection();
		showCollection.updateCollection();
		customerCollection.updateCollection();
		bookingCollection.updateCollection();
	}
	
}