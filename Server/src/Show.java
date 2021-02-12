package src;

import java.util.*;

/**
* @author Anthon Lenander, Erik Kieu, Phong Nguyen
* @version version 0.0.0
*/
public class Show implements AbstractCollectionObject{

	/**
	 * Constructor for initializing the show instance
	 * 
	 * @param movie			the movie that the show will play
	 * @param day_and_time	the day and time of the show
	 * @param cinema			what cinema the show will be played at
	 * @param theater		what theater the show will be played at
	 */
	public Show(Movie movie, Date day_and_time, Cinema cinema, Theater theater)
	{
		
	}
	
	/**
	 * Method for getting the details of the show
	 * 
	 * @return	returns the show object itself
	 */
	public Show GetDetails()
	{
		return this;
	}
	
	/**
	 * Method for finding numOfSeats available seats 
	 * 
	 * @param numOfSeats	the number of seats to find	
	 * @return 				returns a Seat array, containing available seats
	 */
	public Seat[] FindAvailableSeats(int numOfSeats)
	{
		return new Seat[numOfSeats];
	}
	
	/**
	 * Method for finding numOfSeats available seats adjacent to each other
	 * 
	 * @param numOfSeats	the number of seats to find, adjacent to each other
	 * @return 				returns a Seat array, containing available seats adjacent to each other
	 */
	public Seat[] FindAvailableAdjacentSeats(int numOfSeats)
	{
		return new Seat[numOfSeats];
	}
	
	/**
	 * Changes the status of the show
	 */
	public void ChangeStatus()
	{
		
	}

	public Movie getMovie() {
		// TODO Auto-generated method stub
		return null;
	}

	public Show getShow() {
		return this;
	}
}
