import java.io.Serializable;
import java.util.*;

/**
* @author Anthon Lenander, Erik Kieu, Phong Nguyen
* @version version 0.0.0
*/
public class Show implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9177195168561009276L;
	private Movie movie;
	private Date dayAndTime;
	private Cinema cinema;
	private Theater theater;
	private Boolean cancelled;
	
	private Collection<Row> rows;
	
	/**
	 * Constructor for initializing the show instance with corresponding parameter values
	 * 
	 * @param movie			the movie that the show will play
	 * @param dayAndTime	the day and time of the show
	 * @param cinema		what cinema the show will be played at
	 * @param theater		what theater the show will be played at
	 */
	public Show(Movie movie, Date dayAndTime, Cinema cinema, Theater theater)
	{
		this.movie = movie;
		this.dayAndTime = dayAndTime;
		this.cinema = cinema;
		this.theater = theater;
		this.cancelled = false;
		
		this.rows = this.theater.getCollectionOfRows();
	}
	
	/**
	 * Method for getting the movie that will be played
	 * 
	 * @return	returns the movie instance variable of this object
	 */
	public Movie getMovie()
	{
		return this.movie;
	}
	
	/**
	 * Method for getting the day and time at which the show will take place
	 * 
	 * @return	returns the day_and_time instance variable of this object
	 */
	public Date getShowDateAndTime()
	{
		return this.dayAndTime;
	}
	
	/**
	 * Method for getting the cinema at which the show will be played
	 * 
	 * @return	returns the cinema instance variable of this object
	 */
	public Cinema getCinema()
	{
		return this.cinema;
	}
	
	/**
	 * Method for getting the theater at which the show will be played
	 * 
	 * @return	returns the theater instance variable of this object
	 */
	public Theater getTheater()
	{
		return this.theater;
	}
	
	/**
	 * Method for getting this show object
	 * 
	 * @return	returns this show object
	 */
	public Show getShow()
	{
		return this;
	}
	
	/**
	 * Method for finding all seats in the theater that the show will be held in
	 * 
	 * @return 				returns a collection of Seat, containing all seats in the theater
	 */
	public Collection<Seat> getAllSeats()
	{
		Collection<Seat> allSeats = Collections.emptyList();
		
		for(Row r : this.rows)
		{
			allSeats.addAll(r.getAllSeats());
		}
		
		return allSeats;
	}
	
	/**
	 * Method for finding all available seats in the theater that the show will be held in
	 * 
	 * @return 				returns a collection of Seat, containing all available seats
	 */
	public Collection<Seat> getAllAvailableSeats()
	{
		Collection<Seat> allAvailableSeats = Collections.emptyList();
		
		for(Row r : this.rows)
		{
			allAvailableSeats.addAll(r.getAllAvailableSeats());
		}
		
		return allAvailableSeats;
	}
	
	/**
	 * Method for finding numOfSeats available seats in the theater that the show will be held in
	 * 
	 * @param numOfSeats	the number of seats to find	
	 * @return 				returns a collection of Seat, containing numOfSeats available seats
	 */
	public Collection<Seat> getAvailableSeats(int numOfSeats)
	{
		Collection<Seat> availableSeats = Collections.emptyList();
		
		for(Row r : this.rows)
		{
			availableSeats = r.getAvailableSeats(numOfSeats);
			
			if(availableSeats != Collections.<Seat>emptyList())
			{
				return availableSeats;
			}
		}
		
		return availableSeats;
	}
	
	/**
	 * Method for finding numOfSeats available seats adjacent to each other
	 * 
	 * @param numOfSeats	the number of seats to find, adjacent to each other
	 * @return 				returns a collection of Seat, containing numOfSeats adjacent available seats
	 */
	public Collection<Seat> getAdjacentAvailableSeats(int numOfSeats)
	{
		Collection<Seat> availableAdjacentSeats = Collections.emptyList();
		
		for(Row r : this.rows)
		{
			availableAdjacentSeats = r.getAdjacentAvailableSeats(numOfSeats);
			
			if(availableAdjacentSeats != Collections.<Seat>emptyList())
			{
				return availableAdjacentSeats;
			}
		}
		
		return availableAdjacentSeats;
	}
	
	/**
	 * Toggles the status of the show between cancelled and not cancelled
	 */
	public void ChangeStatus()
	{
		this.cancelled = !this.cancelled;
	}
}