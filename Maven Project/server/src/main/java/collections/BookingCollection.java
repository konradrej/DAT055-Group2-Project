package collections;

import java.io.*;
import java.util.*;
import cinemaObjects.*;

/**
* @author Anthon Lenander, Erik Kieu, Phong Nguyen
* @version version 0.0.0
*/
public class BookingCollection extends AbstractCollection {

	private static final long serialVersionUID = 1450784786789696365L;
	private final Collection<Booking> allBookings;
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
	
	public ArrayList <Booking> getBookingsByCustomer(Customer c){
		ArrayList <Booking> bookings = new ArrayList <>();
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
	public ArrayList <Booking> getBookingsByShow (Show s){
		ArrayList <Booking> bookings = new ArrayList <>();
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
	
	public void addBookings(Show s, Customer c , ArrayList <Row> r) {
		Booking b = new Booking(s, c, r );
		this.allBookings.add(b);
	}
	
	/**
	 * Removes a booking from the objects booking collection
	 * 
	 * @param b - the booking that removes
	 */
	//TODO Make seats of the booking available before removing it completely
	public void removeBooking(Booking b ) {
		for(Booking b2: this.allBookings) {
			if(b2.equals(b) && b.getCancelledStatus()) {
				this.allBookings.remove(b2);
			}
		}
	}
	
	/**
	 * Updates a booking given Show, customer, collection of rows and collection of seats
	 * 
	 * @param b - The booking that is updating
	 * @param s - The updated show
	 * @param c - The updated customer
	 * @param r - The updated collection of row

	 */
	
	public void updateBooking (Booking b , Show s, Customer c , ArrayList <Row> r ) {
		for(Booking b2 : this.allBookings) {
			if(b2.equals(b)) {
				this.allBookings.remove(b2);
				this.addBookings(s, c, r);
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
	 * Read serialized file
	 *
	 * @return BookingCollection of the read file
	 * @exception ClassCastException returns an empty BookingCollection
	 * @exception FileNotFoundException returns an empty BookingCollection
	 * @exception NullPointerException returns an empty BookingCollection
	 * @exception IOException returns null
	 */

	public BookingCollection readCollection() throws IOException {
		try(ObjectInputStream stream = new ObjectInputStream(new FileInputStream(this.filename))){

			BookingCollection readThis = (BookingCollection) stream.readObject();
			System.out.println("File: "  + this.filename + " has been read");
			return readThis;
		}
		catch (ClassNotFoundException | FileNotFoundException | NullPointerException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			return new BookingCollection(this.filename);
		}
		catch (IOException e ){
			System.err.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
}
