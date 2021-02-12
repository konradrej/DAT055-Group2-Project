import java.io.Serializable;
import java.util.*;

/**
* @author Anthon Lenander, Erik Kieu, Phong Nguyen
* @version version 0.0.0
*/
public class BookingCollection implements Serializable, AllCollections{

	private static final long serialVersionUID = 1450784786789696365L;
	private Collection<Booking> allBookings;
	public BookingCollection() {
		allBookings = new LinkedList <Booking>();
	}
	
	public Collection <Booking> getBookingsByCustomer(Customer c){
		Collection <Booking> bookings = new LinkedList <Booking>();
		for(Booking b : this.allBookings) {
			Customer c2 = b.getCustomer();
			if(c.equals(c2)) {
				bookings.add(b);
			}
		}
		return bookings;
	}
	
	public Collection <Booking> getfindBookingsByShow (Show s){
		Collection <Booking> bookings = new LinkedList <Booking>();
		for(Booking b : this.allBookings) {
			//TODO: class Show method getShow()
			Show s2 = s.getShow();
			if(s.equals(s2)) {
				bookings.add(b);
			}
		}
		return bookings;
	}
	
	public void addBookings(Show s, Customer c , Row [] r, Seat [] seat ) {
		Booking b = new Booking(s, c, r, seat );
		this.allBookings.add(b);
	}
	
	public void updateBooking (int row, int seat) {
		
	}

	@Override
	public Collection<AbstractCollectionObject> getCollection() {
		Collection<AbstractCollectionObject> c = new LinkedList <AbstractCollectionObject>();
		for(Booking b : this.allBookings) {
			c.add(b);
		}
		return c;
	}

}
