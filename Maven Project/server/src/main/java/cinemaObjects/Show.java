package cinemaObjects;

import java.io.Serializable;
import java.util.*;

/**
* @author Anthon Lenander, Erik Kieu, Phong Nguyen
* @version version 0.0.0
*/
public class Show implements Serializable{

	private static final long serialVersionUID = -9177195168561009276L;
	private final String uniqueID;
	private final Movie movie;
	private final CinemaDate dayAndTime;
	private final Cinema cinema;
	private final Theater theater;
	private Boolean cancelled;
	
	private final Collection<Row> rows;
	
	/**
	 * Constructor for initializing the show instance with corresponding parameter values
	 * 
	 * @param movie			the movie that the show will play
	 * @param dayAndTime	the day and time of the show
	 * @param cinema		what cinema the show will be played at
	 * @param theater		what theater the show will be played at
	 */
	public Show(Movie movie, CinemaDate dayAndTime, Cinema cinema, Theater theater)
	{
		this.movie = movie;
		this.dayAndTime = dayAndTime;
		this.cinema = cinema;
		this.theater = theater;
		this.cancelled = false;
		this.rows = this.theater.getCollectionOfRows();
		this.uniqueID = UUID.randomUUID().toString();
	}

	public String getUniqueID (){
		return this.uniqueID;
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
	 * S
	 * @return	returns the day_and_time instance variable of this object
	 */
	public CinemaDate getShowDateAndTime()
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
	//TODO Should seats be mapped to a specific row? For example Map(row, seat)
	public ArrayList<Seat> getAllSeats()
	{
		ArrayList<Seat> allSeats = new ArrayList<>();
		
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
	public ArrayList<Seat> getAllAvailableSeats()
	{
		ArrayList<Seat> allAvailableSeats = new ArrayList<>();
		
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
	//TODO Vad gör denna?
	public ArrayList<Seat> getAvailableSeats(int numOfSeats)
	{
		ArrayList<Seat> availableSeats = new ArrayList <> ();
		
		for(Row r : this.rows)
		{
			availableSeats = r.getAvailableSeats(numOfSeats);
			
			if(availableSeats.size() != 0)
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
	public ArrayList<Seat> getAdjacentAvailableSeats(int numOfSeats)
	{
		ArrayList<Seat> availableAdjacentSeats = new ArrayList <> ();
		
		for(Row r : this.rows)
		{
			availableAdjacentSeats = r.getAdjacentAvailableSeats(numOfSeats);
			
			if(availableAdjacentSeats.size() != 0)
			{
				return availableAdjacentSeats;
			}
		}
		
		return availableAdjacentSeats;
	}
	
	/**
	 * Toggles the status of the show between cancelled and not cancelled
	 */
	public void cancelShow()
	{
		this.cancelled = true;
	}

	/**
	 * A method to get the Movie , Cinema, Theater and time of the show
	 * @return a string representing the show
	 */

	public String toString(){
		return "Movie: " + this.movie.getTitle() + "\nDate and time: " + this.dayAndTime.toString() +
				"\nCinema: " + this.cinema.getCinemaName() + "\nTheater Number: " + theater.getTheaterNumber();
	}
}