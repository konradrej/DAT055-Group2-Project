import java.io.Serializable;
import java.util.*;

/**
* @author Anthon Lenander, Erik Kieu, Phong Nguyen
* @version version 0.0.0
*/
public class Booking implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 408224943807153172L;
	private Show show;
	private Customer customer;
	private Collection<Row> rows;
	private Collection<Seat> seats;
	private Boolean cancelled;
	
	/**
	 * Constructor for initializing the Booking instance
	 * 
	 * @param show			what show is booked
	 * @param customer		which customer the booking belongs to
	 * @param rows			what rows the customer has booked
	 * @param seats			what seat numbers the customer has booked
	 */
	public Booking(Show show, Customer customer, Collection<Row> rows, Collection<Seat> seats)
	{
		this.show = show;
		this.customer = customer;
		this.rows = rows;
		this.seats = seats;
		this.cancelled = false;
	}
	
	/**
	 * Method for getting what rows the customer has booked seats in
	 * 
	 * @return rows		what rows the customer has booked seats in
	 */
	public Collection<Row> GetBookedRows()
	{
		return this.rows;
	}
	
	/**
	 * Method for getting the seats that the customer has booked
	 * 
	 * @return rows		what seats the customer has booked
	 */
	public Collection<Seat> GetBookedSeats()
	{
		return this.seats;
	}
	
	/**
	 * Method for internally changing the status of the booking
	 */
	public void ChangeStatus()
	{
		this.cancelled = !this.cancelled;
	}
	
	/**
	 * Method for updating what rows and seats the customer has booked
	 * 
	 * @param rows			what rows the customer has booked
	 * @param seats			what seat numbers the customer has booked
	 */
	public void UpdateRowsAndSeats(Collection<Row> rows, Collection<Seat> seats)
	{
		this.rows = rows;
		this.seats = seats;
	}

	/**
	 * Method for getting what customer the booking belongs to
	 * 
	 * @return customer		what customer the booking belongs to
	 */
	public Customer GetCustomer() {
		return this.customer;
	}
	
}