import java.io.Serializable;
import java.util.*;

/**
* @author Anthon Lenander, Erik Kieu, Phong Nguyen
* @version version 0.0.0
*/
public class ShowCollection implements Serializable, AllCollections {
	
	private static final long serialVersionUID = 368284506033169560L;
	private Collection <Show> allShows;
	
	public ShowCollection() {
		allShows = new LinkedList <Show> ();
	}
	
	public Collection <Show> getSelectedShow(Movie m){
		Collection <Show> selectedShows = new LinkedList <Show>();
		//TODO: Date dayAndtime
		// class Show method getMovie();
		for(Show s : this.allShows) {
			Movie m2 = s.getMovie();
			if(m.equals(m2)) {
				selectedShows.add(s);
			}
		}
		return selectedShows;
	}
	
	public void addShow (Movie m, Date dat, Cinema c, Theater t) {
		//TODO: Show parameter
		Show s = new Show(m, dat, c, t);
		this.allShows.add(s);
	}
	
	public void updateShow(Show s, Movie m , Date dat, Cinema c, Theater t) {
		//TODO: New instances for the updated shows
		for(Show s2 : allShows) {
			if(s.equals(s2)) {
				s2 = new Show(m, dat, c, t);
				break;
			}
		}
		System.out.println("Did not found show");
	}
	
	public Collection<Show> getAllShows(){
		return this.allShows;
	}

	@Override
	public Collection<AbstractCollectionObject> getCollection() {
		Collection<AbstractCollectionObject> c = new LinkedList <AbstractCollectionObject>();
		for(Show s : this.allShows) {
			c.add(s);
		}
		return c;
	}
	
	
}
