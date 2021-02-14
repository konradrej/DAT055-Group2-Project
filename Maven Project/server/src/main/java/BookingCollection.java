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
	
	public BookingCollection(String filename) {
		this.filename = filename; 
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
			Show s2 = s.getShow();
			if(s.equals(s2)) {
				bookings.add(b);
			}
		}
		return bookings;
	}
	
	public void addBookings(Show s, Customer c , Collection <Row> r , Collection <Seat> seat ) {
		Booking b = new Booking(s, c, r, seat );
		this.allBookings.add(b);
	}
	
	public void removeBooking(Booking b ) {
		for(Booking b2: this.allBookings) {
			if(b2.equals(b)) {
				this.allBookings.remove(b2);
			}
		}
		System.out.println("Booking not found");
	}
	
	public void updateBooking (Booking b , Show s, Customer c , Collection <Row> r , Collection <Seat> seat ) {
		for(Booking b2 : this.allBookings) {
			if(b2.equals(b)) {
				b2 = new Booking(s, c, r, seat);
			}
		}
	}

	public String getFilname() {
		return this.filename;
	}

	@Override
	public void updateCollection(){
		
		try {
		ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(new File(this.filename)));
		stream.writeObject(this);
		stream.close();
		}
		catch (IOException e) {
            System.out.println("Error initializing stream");
        }
		
	}
	

}
