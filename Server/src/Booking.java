import java.util.*;

/**
* @author Anthon Lenander, Erik Kieu, Phong Nguyen
* @version version 0.0.0
*/
public class Booking {
	
	private Show show;
	private Customer customer;
	private Collection<Row> rows;
	private Collection<Seat> seats;
	private Boolean status;
	
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
	 * Method for changing the status of the booking
	 */
	public void ChangeStatus()
	{
		//TODO Implement status of the booking and make it possible to change it via this method
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
	
}