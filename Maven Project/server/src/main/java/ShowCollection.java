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
	
	public ShowCollection(String filename) {
		this.filename = filename;
		allShows = new LinkedList <Show> ();
	}
	
	public Collection <Show> getSelectedShow(Movie m){
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
	
	public void addShow (Movie m, Date dat, Cinema c, Theater t) {
		Show s = new Show(m, dat, c, t);
		this.allShows.add(s);
	}
	
	public void removeShow(Show s ) {
		for(Show s2: this.allShows) {
			if(s2.equals(s)) {
				this.allShows.remove(s2);
			}
		}
		System.out.println("Show not found");
	}
	
	public void updateShow(Show s, Movie m , Date dat, Cinema c, Theater t) {
		for(Show s2 : allShows) {
			if(s.equals(s2)) {
				s2 = new Show(m, dat, c, t);
				break;
			}
		}
		System.out.println("Show not found");
	}
	
	public Collection<Show> getAllShows(){
		return this.allShows;
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
            System.out.println("Error initializing stream");
        }
		
	}
	
}
