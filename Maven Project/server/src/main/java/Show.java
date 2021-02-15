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
	private Date day_and_time;
	private Cinema cinema;
	private Theater theater;
	private Boolean cancelled;
	
	private Collection<Row> rows;
	
	/**
	 * Constructor for initializing the show instance with corresponding parameter values
	 * 
	 * @param movie			the movie that the show will play
	 * @param day_and_time	the day and time of the show
	 * @param cinema		what cinema the show will be played at
	 * @param theater		what theater the show will be played at
	 */
	public Show(Movie movie, Date day_and_time, Cinema cinema, Theater theater)
	{
		this.movie = movie;
		this.day_and_time = day_and_time;
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
	public Movie GetMovie()
	{
		return this.movie;
	}
	
	/**
	 * Method for getting the day and time at which the show will take place
	 * 
	 * @return	returns the day_and_time instance variable of this object
	 */
	public Date GetShowDateAndTime()
	{
		return this.day_and_time;
	}
	
	/**
	 * Method for getting the cinema at which the show will be played
	 * 
	 * @return	returns the cinema instance variable of this object
	 */
	public Cinema GetCinema()
	{
		return this.cinema;
	}
	
	/**
	 * Method for getting the theater at which the show will be played
	 * 
	 * @return	returns the theater instance variable of this object
	 */
	public Theater GetTheater()
	{
		return this.theater;
	}
	
	/**
	 * Method for finding numOfSeats available seats 
	 * 
	 * @param numOfSeats	the number of seats to find	
	 * @return 				returns a Seat array, containing available seats
	 */
	public Collection<Seat> getAvailableSeats(int numOfSeats)
	{
		Collection<Seat> availableSeats = new LinkedList<Seat>();
		
		for(Row r : this.rows)
		{
			for(Seat s : r.getAllSeats())
			{
				if(s.getAvailable() && availableSeats.size() < numOfSeats)
				{
					availableSeats.add(s);
				}
			}
		}
		
		return availableSeats;
	}
	
	/**
	 * Method for finding numOfSeats available seats adjacent to each other
	 * 
	 * @param numOfSeats	the number of seats to find, adjacent to each other
	 * @return 				returns a Seat array, containing available seats adjacent to each other
	 */
	public Collection<Seat> GetAdjacentAvailableSeats(int numOfSeats)
	{
		Collection<Seat> availableSeatsOfRow = new LinkedList<Seat>();
		
		for(Row r : this.rows)
		{
			for(Seat s : r.getAllSeats())
			{
				if(s.getAvailable())
				{
					availableSeatsOfRow.add(s);
				}
			}
			
			Seat[] availableSeatsOfRowArray = (Seat[])availableSeatsOfRow.toArray();
			Seat[] availableAdjacentSeatsOfRowArray = new Seat[numOfSeats];
			
			for(int i = 0; i < availableSeatsOfRowArray.length - numOfSeats; i++)
			{
				boolean availableAdjacent = true;
				
				for(int j = i; j <= numOfSeats; j++)
				{
					if(!availableSeatsOfRowArray[j].getAvailable())
					{
						availableAdjacent = false;
						break;
					}
					else availableAdjacentSeatsOfRowArray[j] = availableSeatsOfRowArray[i];
				}
				
				if(availableAdjacent)
				{
					Collection<Seat> availableAdjacentSeats = new LinkedList<Seat>();
					
					for(int j = 0; j < numOfSeats; j++)
					{
						availableAdjacentSeats.add(availableAdjacentSeatsOfRowArray[j]);
					}
					
					return availableAdjacentSeats;
				}
				else availableAdjacentSeatsOfRowArray = new Seat[numOfSeats];
			}
		}
		return new LinkedList<Seat>();
	}
	
	/**
	 * Toggles the status of the show between cancelled and not cancelled
	 */
	public void ChangeStatus()
	{
		this.cancelled = !this.cancelled;
	}

	// TODO Comments
	public Movie getMovie() {
		// TODO Auto-generated method stub
		return null;
	}

	// TODO Comments
	public Show getShow() {
		return this;
	}
	
	public Theater getTheater() {
		return this.theater;
	}
}
