import java.util.*;

/**
* @author Anthon Lenander, Erik Kieu, Phong Nguyen
* @version version 0.0.0
*/
public class ShowCollection {
	private LinkedList <Show> allShows = new LinkedList <Show> ();
	
	public ShowCollection() {
	}
	
	public LinkedList <Show> getSelectedShow(Movie m){
		LinkedList <Show> selectedShows = new LinkedList <Show>();
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
	
	public void updateShow(Show s) {
		//TODO: New instances for the updated shows
	}
}
