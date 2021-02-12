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
	
	public void updateCollections(){
		//Movie
		try {
		ObjectOutputStream movieStream = new ObjectOutputStream(new FileOutputStream(new File("ServerMovieCollection")));
		movieStream.writeObject(this.movieCollection);
		movieStream.close();
		}
		catch (IOException e) {
            System.out.println("Error initializing stream");
        } 
		catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		
		//Booking
		try {
		ObjectOutputStream bookingStream = new ObjectOutputStream(new FileOutputStream(new File("ServerBookingCollection")));
		bookingStream.writeObject(this.bookingCollection);
		bookingStream.close();
		}
		catch (IOException e) {
            System.out.println("Error initializing stream");
        } 
		catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		
		//Customer
		
		try {
		ObjectOutputStream customerStream = new ObjectOutputStream(new FileOutputStream(new File("ServerCustomerCollection")));
		customerStream.writeObject(this.customerCollection);
		customerStream.close();
		}
		catch (IOException e) {
            System.out.println("Error initializing stream");
        } 
		catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		
		//Show
		
		try {
		ObjectOutputStream showStream = new ObjectOutputStream(new FileOutputStream(new File("ServerShowCollection")));
		showStream.writeObject(this.movieCollection);
		showStream.close();
		}
		catch (IOException e) {
            System.out.println("Error initializing stream");
        } 
		catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
	}
	
}