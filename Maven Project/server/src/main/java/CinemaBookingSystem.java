import server.ServerHandler;

import java.util.Collection;
import java.util.LinkedList;

public enum CinemaBookingSystem implements ServerHandler {

	INSTANCE();

	/**
	 * Constructor for initializing the CinemaBookingSystem instance
	 *
	 * @param bookingCollection 	a collection of bookings
	 * @param customerCollection	a collection of customers
	 * @param showCollection		a collection of shows
	 * @param movieCollection		a collection of movies
	 */

	private final Cinema cinema;
	private BookingCollection bookingCollection = new BookingCollection("bookingCollection.txt");
	private MovieCollection movieCollection = new MovieCollection("movieCollection.txt");
	private ShowCollection showCollection = new ShowCollection("showCollection.txt");
	private CustomerCollection customerCollection = new CustomerCollection("customerCollection.txt");


	CinemaBookingSystem() {

		Collection<Seat> seatTen = new LinkedList<>();
		for(int i = 0; i < 10; i++){
			seatTen.add(new Seat(i+1, true));
		}

		Collection <Seat> seatFifteen = new LinkedList<>();
		for(int i = 0; i < 15 ; i++){
			seatFifteen.add(new Seat(i+1, true));
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
		this.movieCollection =  movieCollection.readCollection();
		this.showCollection = showCollection.readCollection();
		this.customerCollection = customerCollection.readCollection();
		this.bookingCollection =  bookingCollection.readCollection();
	}

	public BookingCollection getBookingCollection(){
		return bookingCollection;
	}

	public MovieCollection getMovieCollection(){
		return movieCollection;
	}

	public ShowCollection getShowCollection(){
		return showCollection;
	}

	public CustomerCollection getCustomerCollection(){
		return customerCollection;
	}

	public Cinema getCinema() {
		return cinema;
	}
}