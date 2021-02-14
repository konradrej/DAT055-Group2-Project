import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

/**
* @author Anthon Lenander, Erik Kieu, Phong Nguyen
* @version version 0.0.0
*/
public class ShowCollection implements Serializable, AllCollections {
	
	private static final long serialVersionUID = 368284506033169560L;
	private Collection <Show> allShows;
	private final String filename;
	
	/**
	 * Constructor for initializing the ShowCollection instance
	 * 
	 * @param filename	Filename of the object when serializing
	 */
	
	public ShowCollection(String filename) {
		this.filename = filename;
		allShows = new LinkedList <Show> ();
	}
	
	/**
	 * Get a collection of selected shows given movie
	 * 
	 * @param d (TODO: date and time) -
	 * @param m - the movie being searched
	 * @return a collection of shows
	 */
	
	public Collection <Show> getShowsGivenMovie(Movie m){
		Collection <Show> selectedShows = new LinkedList <Show>();
		//TODO: Date dayAndtime
		for(Show s : this.allShows) {
			Movie m2 = s.getMovie();
			if(m.equals(m2)) {
				selectedShows.add(s);
			}
		}
		return selectedShows;
	}
	
	/**
	 * Adds a show given movie, date and time, cinema and theater:
	 * 
	 * @param m - The movie of the show
	 * @param dat - The date and time of the show
	 * @param c - The cinema of the show
	 * @param t - The theater of the show
	 */
	
	public void addShow (Movie m, Date dat, Cinema c, Theater t) {
		Show s = new Show(m, dat, c, t);
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
	
	public void updateShow(Show s, Movie m , Date dat, Cinema c, Theater t) {
		for(Show s2 : allShows) {
			if(s.equals(s2)) {
				s2 = new Show(m, dat, c, t);
				break;
			}
		}
		System.out.println("Show not found");
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

	@Override
	public void updateCollection() {

		try {
		ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(new File(this.filename)));
		stream.writeObject(this);
		stream.close();
		}
		catch (IOException e) {
            System.out.println("Error initializing stream");
        }
		
	}
	
}