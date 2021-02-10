import java.util.*;

/**
* @author Anthon Lenander, Erik Kieu, Phong Nguyen
* @version version 0.0.0
*/
public class MovieCollection {
	private Collection <Movie> allMovies;
	
	public MovieCollection() {
		
	}
	
	public Collection <Movie> getAllMovies(){
		return this.allMovies;
	}
	
	public Collection <Movie> getSelectedMovies(String title, String genre){
		Collection <Movie> selectedMovies = null;
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
