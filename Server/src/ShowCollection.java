import java.util.*;

import src.Show;

/**
* @author Anthon Lenander, Erik Kieu, Phong Nguyen
* @version version 0.0.0
*/
public class ShowCollection {
	private Collection <Show> allShows;
	
	public Collection <Show> getSelectedShow(Movie m){
		Collection <Show> selectedShows = null;
		//TODO: Date dayAndtime
		// class Show method getMovie();
		for(Show s : this.allShows) {
			Movie m2 = s2.getMovie();
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
