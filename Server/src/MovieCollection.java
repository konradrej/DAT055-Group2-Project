
import java.io.Serializable;
import java.util.*;

/**
* @author Anthon Lenander, Erik Kieu, Phong Nguyen
* @version version 0.0.0
*/
public class MovieCollection implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LinkedList <Movie> allMovies;
	
	public MovieCollection() {
		allMovies = new LinkedList<Movie>();
	}
	
	public LinkedList <Movie> getAllMovies(){
		return this.allMovies;
	}
	
	public LinkedList <Movie> getSelectedMovies(String title, String genre){
		LinkedList <Movie> selectedMovies = new LinkedList<Movie>();
		for(Movie m : this.allMovies) {
			if(title.equals(m.getTitle()) || genre.equals(m.getGenre())) {
				selectedMovies.add(m);
			}
		}
		return selectedMovies;
	}
	
	public void addMovies(Movie m) {
		this.allMovies.add(m);
	}
	

}
