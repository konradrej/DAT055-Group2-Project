import java.io.Serializable;
import java.util.*;

/**
* @author Anthon Lenander, Erik Kieu, Phong Nguyen
* @version version 0.0.0
*/
public class BookingCollection implements Serializable{

	private static final long serialVersionUID = 1L;
	private final int BookingCollectionID;
	private LinkedList<Booking> allBookings = new LinkedList <Booking>();
	
	public BookingCollection(final int ID) {
		BookingCollectionID = ID;
	}
	
	public LinkedList <Booking> getBookingsByCustomer(Customer c){
		LinkedList <Booking> bookings = new LinkedList <Booking>();
		for(Booking b : this.allBookings) {
			//TODO: class Customer method getCustomer()
			Customer c2 = b.getCustomer();
			if(c.equals(c2)) {
				bookings.add(b);
			}
		}
		return bookings;
	}
	
	public LinkedList <Booking> getfindBookingsByShow (Show s){
		LinkedList <Booking> bookings = new LinkedList <Booking>();
		for(Booking b : this.allBookings) {
			//TODO: class Show method getShow()
			Show s2 = s.getShow();
			if(s.equals(s2)) {
				bookings.add(b);
			}
		}
		return bookings;
	}
	
	public void addBookings (Show s, Customer c , Row [] r, Seat [] seat ) {
		Booking b = new Booking(s, c, r, seat );
		this.allBookings.add(b);
	}
	
	public void updateBooking (int row, int seat) {
		
	}

}
