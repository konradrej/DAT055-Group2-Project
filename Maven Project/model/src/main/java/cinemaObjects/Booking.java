package cinemaObjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class handles the booking for a customer
 * and stores a show, customer information, row
 * and seat number for all booked seats.
 *
 * @author Erik Kieu
 * @version 2021-03-02
 */
public class Booking implements Serializable {

	private static final long serialVersionUID = 408224943807153172L;
	private Show show;
	private Customer customer;
	private List<Row> rows;
	private Boolean cancelled;
	
	/**
	 * Constructor for initializing the cinemaObjects.Booking instance
	 * 
	 * @param show			what show is booked
	 * @param customer		which customer the booking belongs to
	 * @param rows			what rows the customer has booked
	 */
	public Booking(Show show, Customer customer, List<Row> rows)
	{
		this.show = show;
		this.customer = customer;
		this.rows = rows;
		this.cancelled = false;
	}
	
	/**
	 * Method for getting what rows the customer has booked seats in
	 * 
	 * @return rows		what rows the customer has booked seats in
	 */
	public List<Row> getBookedRows()
	{
		return this.rows;
	}
	
	/**
	 * Method for getting the seats in row with rowNumber that the customer has booked
	 * 
	 * @param  rowNumber	what row the seats are located in
	 * @return rows			what seats the customer has booked
	 */
	public List<Seat> getBookedSeats(int rowNumber)
	{
		List<Seat> bookedSeats = new ArrayList <>();
		
		for(Row r : this.rows)
		{
			if(r.getRowNumber() == rowNumber)
			{
				bookedSeats = r.getAllSeats();
			}
		}
		
		return bookedSeats;
	}
	
	/**
	 * Method for internally changing the status of the booking
	 */
	public void cancelBooking()
	{

		this.cancelled = true;

		for(Row r : rows)
		{
			for(Seat s : r.getAllSeats())
			{
				s.updateSeatStatus(false);
			}
		}

	}

	/**
	 * Method for getting the status of this particular booking
	 *
	 * @return	returns the cancelled status of this particular booking
	 */
	public Boolean getCancelledStatus() { return cancelled; }
	
	/**
	 * Method for updating what rows and seats the customer has booked
	 * 
	 * @param rows			what rows the customer has booked
	 * @param seats			what seat numbers the customer has booked
	 */
	public void updateRowsAndSeats(List<Row> rows, List<Seat> seats)
	{
		this.rows = rows;
	}

	/**
	 * Method for getting what customer the booking belongs to
	 * 
	 * @return customer		what customer the booking belongs to
	 */
	public Customer getCustomer() {
		return this.customer;
	}

	/**
	 * Method for getting the show instance of this booking
	 *
	 * @return returns the show instance of this booking
	 */
	public Show getShow(){ return this.show;}

}