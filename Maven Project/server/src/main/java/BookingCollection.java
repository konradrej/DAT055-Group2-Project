import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

/**
* @author Anthon Lenander, Erik Kieu, Phong Nguyen
* @version version 0.0.0
*/
public class BookingCollection implements Serializable, AllCollections{

	private static final long serialVersionUID = 1450784786789696365L;
	private Collection<Booking> allBookings;
	private final String filename;
	
	/**
	 * Constructor for initializing the BookingCollection instance
	 * 
	 * @param filename	Filename of the object when serializing
	 */
	
	public BookingCollection(String filename) {
		this.filename = filename; 
		allBookings = new LinkedList <>();
	}
	
	/**
	 * Get all bookings given a customer
	 * 
	 * @param c - the customer
	 * @return a collection of bookings of the customer
	 */
	
	public Collection <Booking> getBookingsByCustomer(Customer c){
		Collection <Booking> bookings = new LinkedList <>();
		for(Booking b : this.allBookings) {
			Customer c2 = b.getCustomer();
			if(c.equals(c2)) {
				bookings.add(b);
			}
		}
		return bookings;
	}
	
	/**
	 * Get all bookings given a show
	 * 
	 * @param s - the show 
	 * @return a collection of bookings of the show
	 */
	public Collection <Booking> getBookingsByShow (Show s){
		Collection <Booking> bookings = new LinkedList <>();
		for(Booking b : this.allBookings) {
			Show s2 = s.getShow();
			if(s.equals(s2)) {
				bookings.add(b);
			}
		}
		return bookings;
	}
	
	/**
	 * Adds booking to the objects booking collection given show, customer, collection of rows and collection of seats
	 * 
	 * @param s - The show of the booking
	 * @param c - The customer of the booking
	 * @param r - (TODO: ) The collection of row of the booking

	 */
	
	public void addBookings(Show s, Customer c , Collection <Row> r) {
		Booking b = new Booking(s, c, r );
		this.allBookings.add(b);
	}
	
	/**
	 * Removes a booking from the objects booking collection
	 * 
	 * @param b - the booking that removes
	 */
	
	public void removeBooking(Booking b ) {
		for(Booking b2: this.allBookings) {
			if(b2.equals(b)) {
				this.allBookings.remove(b2);
			}
		}
		System.out.println("Booking not found");
	}
	
	/**
	 * Updates a booking given Show, customer, collection of rows and collection of seats
	 * 
	 * @param b - The booking that is updating
	 * @param s - The updated show
	 * @param c - The updated customer
	 * @param r - The updated collection of row

	 */
	
	public void updateBooking (Booking b , Show s, Customer c , Collection <Row> r ) {
		for(Booking b2 : this.allBookings) {
			if(b2.equals(b)) {
				//TODO: fixa
				b2 = new Booking(s, c, r);
			}
		}
	}
	
	/**
	 * Get method for the filename
	 * 
	 * @return the filename when serializing
	 */

	public String getFilname() {
		return this.filename;
	}

	/**
	 * Serializing and updates this object 
	 * 
	 * 	@Override
	 */
	public void updateCollection(){
		
		try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(new File(this.filename)))){
		stream.writeObject(this);
		}
		catch (IOException e) {
            System.out.println("Error initializing stream");
        }
		
	}
	

}
