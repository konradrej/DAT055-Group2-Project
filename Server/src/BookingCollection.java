import java.util.*;

/**
* @author Anthon Lenander, Erik Kieu, Phong Nguyen
* @version version 0.0.0
*/
public class BookingCollection {
	private Collection <Booking> allBookings;
	
	public BookingCollection() {
		
	}
	
	public Collection <Booking> getBookingsByCustomer(Customer c){
		Collection <Booking> bookings = null;
		for(Booking b : this.allBookings) {
			//TODO: class Customer method getCustomer()
			Customer c2 = b.getCustomer();
			if(c.equals(c2)) {
				bookings.add(b);
			}
		}
		return bookings;
	}
	
	public Collection <Booking> getfindBookingsByShow (Show s){
		Collection <Booking> bookings;
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
		//något här
		Booking b = new Booking(s, c, r, seat );
		this.allBookings.add(b);
	}
	
	public void updateBooking (int row, int seat) {
		
	}

}
