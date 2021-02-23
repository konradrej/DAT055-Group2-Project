import server.ServerHandler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public enum CinemaBookingSystem implements ServerHandler {

	INSTANCE();

	private final Cinema cinema;
	private BookingCollection bookingCollection = new BookingCollection("bookingCollection.txt");
	private MovieCollection movieCollection = new MovieCollection("movieCollection.txt");
	private ShowCollection showCollection = new ShowCollection("showCollection.txt");
	private CustomerCollection customerCollection = new CustomerCollection("customerCollection.txt");

	/**
	 * Constructor for initializing the CinemaBookingSystem instance
	 */
	CinemaBookingSystem() {

		Collection<Seat> seatTen = new LinkedList<>();
		for(int i = 0; i < 10; i++){
			seatTen.add(new Seat(i+1));
		}

		Collection <Seat> seatFifteen = new LinkedList<>();
		for(int i = 0; i < 15 ; i++){
			seatFifteen.add(new Seat(i+1));
		}

		Collection<Row> rowFive = new LinkedList <>();
		rowFive.add(new Row(1, seatTen));
		rowFive.add(new Row(2, seatTen));
		rowFive.add(new Row(3, seatFifteen));
		rowFive.add(new Row(4, seatFifteen));
		rowFive.add(new Row(5, seatFifteen));


		Collection <Row> rowThree = new LinkedList<>();
		rowThree.add(new Row(1, seatTen));
		rowThree.add(new Row(2, seatTen));
		rowThree.add(new Row(3, seatFifteen));

		Collection <Theater> theaterFive = new LinkedList<>();
		theaterFive.add(new Theater(1, rowFive));
		theaterFive.add(new Theater(2, rowThree));
		theaterFive.add(new Theater(3, rowThree));
		theaterFive.add(new Theater(4, rowFive));
		theaterFive.add(new Theater(5, rowFive));

		cinema = new Cinema("Underground Bio",theaterFive);

	}

	public static CinemaBookingSystem getInstance(){
		return INSTANCE;
	}



	// Serialize (saves in path: Working Tree-> Maven project -> Server) all collections

	public void updateAllCollections(){
		movieCollection.updateCollection(movieCollection.getFilename());
		showCollection.updateCollection(showCollection.getFilename());
		customerCollection.updateCollection(customerCollection.getFilename());
		bookingCollection.updateCollection(bookingCollection.getFilname());
	}

	public void readAllCollections(){

		this.movieCollection = movieCollection.readCollection();
		this.showCollection = showCollection.readCollection();
		this.customerCollection = customerCollection.readCollection();
		this.bookingCollection =  bookingCollection.readCollection();
	}

	public BookingCollection getBookingCollection(){
		return bookingCollection;
	}

	public MovieCollection getMovieCollection(){
		return this.movieCollection;
	}

	public ShowCollection getShowCollection() { return this.showCollection; }

	public ShowCollection getShowsByMovie(Object movie)
	{
		try {
			Movie m = (Movie) movie;
			return showCollection.getShowsGivenMovie(m);
		}
		catch (ClassCastException e){
			e.printStackTrace();
		}
		return new ShowCollection("empty");
	}

	public Collection<Seat> getAllSeatsByShow(Object show)
	{
		Collection<Seat> seatsByShow = new ArrayList<>();

		for(Show show1 : this.getShowCollection().getAllShows())
		{
			if(show1.equals(show))
			{
				seatsByShow.addAll(show1.getAllSeats());
			}
		}

		return seatsByShow;
	}

	public Customer getCustomerBySSN(String SSN)
	{
		return this.customerCollection.getCustomer(SSN);
	}

	public Collection<Booking> getBookingsBySSN(String SSN)
	{
		Customer customer = getCustomerBySSN(SSN);

		return this.bookingCollection.getBookingsByCustomer(customer);
	}

	public CustomerCollection getCustomerCollection(){
		return customerCollection;
	}

	public Cinema getCinema() {
		return cinema;
	}

	public String createBooking(Object show, Object customer, Collection<Object> rows)
	{
		Collection<Row> bookedRows = Collections.emptyList();

		try {
			for(Object r : rows)
			{
				bookedRows.add((Row)r);
			}
		} catch (ClassCastException e) {
			System.err.println("Class could not be casted. Message: " + e.getMessage());
			e.printStackTrace();
			return "Booking failed";
		} finally {
			this.bookingCollection.addBookings((Show)show, (Customer)customer, bookedRows);
		}

		return "Booking successfully created";
	}

	public String createCustomer(String name, String phoneNumber, String SSN)
	{
		Customer newCustomer = null;

		try {
			newCustomer = new Customer(name, phoneNumber, SSN);
		} catch (IllegalArgumentException e) {
			System.err.println("Argument are of wrong type. Message: " + e.getMessage());
			e.printStackTrace();
			return "Creation of customer failed";
		} finally {
			this.customerCollection.addCustomer(newCustomer);
		}

		return "Customer successfully created";
	}

	public void cancelBooking(Booking booking) { booking.cancelBooking(); }
}