package collections;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import cinemaObjects.*;

/**
* @author Anthon Lenander, Erik Kieu, Phong Nguyen
* @version version 0.0.0
*/
public class BookingCollection extends AbstractCollection {

	private static final long serialVersionUID = 1450784786789696365L;
	private final List<Booking> allBookings;
	private final String filename;

	/**
	 * Constructor for initializing the BookingCollection instance
	 * 
	 * @param filename	Filename of the object when serializing
	 */
	
	public BookingCollection(String filename) {
		this.filename = filename; 
		allBookings = new ArrayList <>();
	}
	
	/**
	 * Get all bookings given a customer
	 * 
	 * @param c - the customer
	 * @return a collection of bookings of the customer
	 */
	
	public List <Booking> getBookingsByCustomer(Customer c){
		List <Booking> bookings = new ArrayList <>();
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
	public List <Booking> getBookingsByShow (Show s){
		List <Booking> bookings = new ArrayList <>();
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
	 * @param showCollection - showCollection to update
	 */
	public void addBookings(Show s, Customer c , List<Row> r, ShowCollection showCollection) {
		for(Row row1: r){
			for(Row row : s.getTheater().getCollectionOfRows()){
				if(row.getRowNumber() == row1.getRowNumber()){
					for(Seat seat : row.getAllSeats()){
						for(Seat seat1: row1.getAllSeats()){
							if(seat.getSeatNumber() == seat1.getSeatNumber()){
								seat.updateSeatStatus(false);
							}
						}
					}
				}
			}
		}

		showCollection.updateShow(s, s.getMovie(), s.getShowDateAndTime(), s.getCinema(), s.getTheater());

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
				b2.cancelBooking();
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
	 * @param showCollection - showCollection to update
	 */
	public void updateBooking (Booking b , Show s, Customer c , List <Row> r, ShowCollection showCollection) {
		for(Booking b2 : this.allBookings) {
			if(b2.equals(b)) {
				this.allBookings.remove(b2);
				this.addBookings(s, c, r, showCollection);
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
	 * @exception NullPointerException returns an empty BookingCollection
	 */

	public BookingCollection readCollection() {
		try(ObjectInputStream stream = new ObjectInputStream(new FileInputStream(this.filename))){

			BookingCollection readThis = (BookingCollection) stream.readObject();
			System.out.println("File: "  + this.filename + " has been read");
			return readThis;
		}
		catch (ClassCastException | ClassNotFoundException | FileNotFoundException | NullPointerException e) {
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
