
import java.io.Serializable;
import java.util.*;

/**
* @author Anthon Lenander, Erik Kieu, Phong Nguyen
* @version version 0.0.0
*/
public class MovieCollection implements Serializable, AllCollections{

	private static final long serialVersionUID = -6417208744468074004L;
	private Collection <Movie> allMovies;
	
	public MovieCollection(final int ID) {
		allMovies = new LinkedList<Movie>();
	}
	
	public Collection <Movie> getAllMovies(){
		return this.allMovies;
	}
	
	public Collection <Movie> getSelectedMovies(String title, String genre){
		Collection <Movie> selectedMovies = new LinkedList<Movie>();
		for(Movie m : this.allMovies) {
			if(title.equals(m.getTitle()) || genre.equals(m.getGenre())) {
				selectedMovies.add(m);
			}
		}
		return selectedMovies;
	}
	
	@Override
	public Collection<AbstractCollectionObject> getCollection() {
		Collection <AbstractCollectionObject> c = new LinkedList <AbstractCollectionObject>();
		for(Movie m : this.allMovies) {
			c.add(m);
		}
		return c;
	}
	

}
