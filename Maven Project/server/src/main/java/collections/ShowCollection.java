package collections;

import cinemaObjects.*;

import java.io.*;
import java.util.*;

/**
* @author Anthon Lenander, Erik Kieu, Phong Nguyen
* @version version 0.0.0
*/
public class ShowCollection extends AbstractCollection {

	private static final long serialVersionUID = 368284506033169560L;
	private final Collection <Show> allShows;
	private final String filename;
	
	/**
	 * Constructor for initializing the ShowCollection instance
	 * 
	 * @param filename	Filename of the object when serializing
	 */
	
	public ShowCollection(String filename) {
		this.filename = filename;
		allShows = new LinkedList <>();
	}
	
	/**
	 * Get a collection of selected shows given movie
	 *
	 * @param m - the movie being searched
	 * @return a collection of shows
	 */
	
	public ShowCollection getShowsGivenMovie(Movie m){
		ShowCollection s = new ShowCollection("getShowsGivenMovie");
		for(Show s2 : getAllShows()){
			if( m.getTitle().equals(s2.getMovie().getTitle())){
				s.addShow(s2);
			}
		}
		return s;
	}
	
	public Collection<Seat> getAllAvailableSeats(Show s){
		for(Show s2: this.allShows) {
			if(s2.equals(s)) {
				return s2.getAllAvailableSeats();
			}
		}
		System.out.println("Show not found / no collection of seats available");
		return null;
	}
	
	public Collection <Seat> getAdjacentSeats(Show s, int numOfSeats){
			for(Show s2: this.allShows) {
				if(s2.equals(s)) {
					return s2.getAdjacentAvailableSeats(numOfSeats);
				}
			}
			System.out.println("Show not found / no collection of seats available");
		return null;
	}
	
	
	/**
	 * Adds a show given movie, date and time, cinema and theater:
	 * 
	 * @param s - The show that is added
	 */
	
	public void addShow (Show s) {
		this.allShows.add(s);
	}
	
	/**
	 * Removes a show given show
	 * 
	 * @param s the show that being removed
	 */
	
	public void removeShow(Show s ) {
		for(Show s2: this.allShows) {
			if(s2.equals(s)) {
				this.allShows.remove(s2);
			}
		}
		System.out.println("Show not found");
	}
	
	/**
	 * Update a show given movie, date and time, cinema and theater
	 * 
	 * @param s - The show being updated
	 * @param m - The updated movie of the show
	 * @param dat - The updated day and time of the movie
	 * @param c - The updated cinema of the show
	 * @param t - The updated theater of the show
	 */
	
	public void updateShow(Show s, Movie m , CinemaDate dat, Cinema c, Theater t) {
		for(Show s2 : allShows) {
			if(s.equals(s2)) {
				this.removeShow(s2);
				this.addShow(new Show (m, dat, c, t));
				break;
			}
		}
	}

	public Show getShowByUID(String uid){
		for(Show s : this.getAllShows()){
			if(uid.equals(s.getUniqueID())){
				return s;
			}
		}
		//TODO något annat
		return null;
	}
	
	/**
	 * Get method to get all shows from the objects collection
	 * 
	 * @return - a collection of shows
	 */
	
	public Collection<Show> getAllShows(){
		return this.allShows;
	}
	
	/**
	 * Get method for the filename when serializing
	 * 
	 * @return a string of the filename
	 */
	
	public String getFilename() {
		return this.filename;
	}

	/**
	 * Read serialized file
	 *
	 * @return ShowCollection of the read file
	 * @exception ClassCastException returns an empty ShowCollection
	 * @exception FileNotFoundException returns an empty ShowCollection
	 * @exception NullPointerException returns an empty ShowCollection
	 * @exception IOException returns null
	 */

	public ShowCollection readCollection() throws FileNotFoundException, IOException{
		try(ObjectInputStream stream = new ObjectInputStream(new FileInputStream(new File(this.filename)))){

			ShowCollection readThis = (ShowCollection) stream.readObject();
			System.out.println("File: "  + this.filename + " has been read");
			return readThis;
		}
		catch (ClassNotFoundException | FileNotFoundException | NullPointerException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			return new ShowCollection(this.filename);
		}
		catch (IOException e ){
			System.err.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
}
