
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
public class MovieCollection implements Serializable, AllCollections{

	private static final long serialVersionUID = -6417208744468074004L;
	private Collection <Movie> allMovies;
	private final String filename;
	
	public MovieCollection(String filename) {
		this.filename = filename;
		allMovies = new LinkedList<Movie>();
	}
	
	public Collection <Movie> getAllMovies(){
		return this.allMovies;
	}
	
	public void addMovie(Movie m) {
		this.allMovies.add(m);
	}
	
	public void removeMovie(Movie m ) {
		for(Movie m2: this.allMovies) {
			if(m2.equals(m)) {
				this.allMovies.remove(m2);
			}
		}
		System.out.println("Movie not found");
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
            e.printStackTrace();
        }
		
	}
	
}
