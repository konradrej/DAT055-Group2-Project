import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
* @author Anthon Lenander, Erik Kieu, Phong Nguyen
* @version version 0.0.0
*/
public class MovieCollection implements Serializable, AllCollections{

	private static final long serialVersionUID = -6417208744468074004L;
	private Collection <Movie> allMovies;
	private final String filename;
	private final String url = "https://www.themoviedb.org";
	
	/**
	 * Constructor for initializing the MovieCollection instance
	 * 
	 * @param filename	Filename of the object when serializing
	 */
	
	public MovieCollection(String filename) {
		this.filename = filename;
		allMovies = new LinkedList <Movie>();
	}
	
	/**
	 * @return a string name of the object when serializing
	 */
	
	public String getFilename() {
		return this.filename;
	}
	
	/**
	 * @return a collection of Movies of the objects movie collection
	 */
	
	public Collection <Movie> getAllMovies(){
		return this.allMovies;
	}
	
	/**
	 * Adds a movie to the m to the objects movie collection
	 * 
	 * @param movie to add
	 */
	
	public void addMovie(Movie m) {
		this.allMovies.add(m);
	}
	
	/**
	 * Removes a movie to the m to the objects movie collection
	 * 
	 * @param movie to remove
	 */
	
	public void removeMovie(Movie m ) {
		for(Movie m2: this.allMovies) {
			if(m2.equals(m)) {
				this.allMovies.remove(m2);
			}
		}
		System.out.println("Movie not found");
	}
	
	/**
	 * Get a collection of selected movies with title and genres perferences
	 * 
	 * @param title - selected title for the movies
	 * @param genre - selected genre for the movies
	 */
	
	public Collection <Movie> getSelectedMovies(String title, String genre){
		Collection <Movie> selectedMovies = new LinkedList<Movie>();
		
		for(Movie m : this.allMovies) {
			for(String g : m.getGenre()) {
				if(title.equals(m.getTitle()) || genre.equals(g)) {
					selectedMovies.add(m);
				}
			}
			
		}
		return selectedMovies;
	}
	
	/**
	 * Serializing and updates this object 
	 * 
	 *
	 */
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
	
	/**
	 * Scans and updates new movies to the collection from this objects URL
	 */
	
	public void scanNewMovies() {
		Document doc;
		try {
			doc = Jsoup.connect(url + "/movie/now-playing").timeout(6000).get();
		}
		catch(IOException e) {
			System.out.println("Error");
			return;
		}
		
		Elements body = doc.select("div.page_wrapper");
		for(Element e : body.select("h2 a")) {
			String link = e.attributes().get("href");
			String title = e.attributes().get("title");
			Boolean scanthis = true;
			for( Movie m : this.allMovies) {
				if(m.getURL().equals(url + link)){
					scanthis = false;
					break;
				}
			}
			if(scanthis) {
				scanMovieDetails(link, title);
			}
		}
	}
	
	/**
	 * Scans and adds the movie details: title, length, genres and URL
	 * 
	 * @param l - path to the movie link
	 * @param title - title of the movie being added
	 */
	
	public void scanMovieDetails(String l, String title) {
		Document doc;
		
		try {
			doc = Jsoup.connect(url + l).timeout(6000).get();
		}
		catch(IOException e) {
			System.out.println("Error");
			return;
		}
		
		Elements elementTime = doc.getElementsByClass("runtime");
		Elements elementGenres = doc.select("span.genres");
		ArrayList <String> list = new ArrayList<String>();
		for(Element genre : elementGenres.select("a")) {
			String s = "";

			switch(genre.attributes().get("href")) {
			
			case "/genre/53-thriller/movie": s = "Thriller";
			break;
			case "/genre/80-crime/movie": s = "Crime";
			break;
			case "/genre/27-horror/movie": s = "Horror";
			break;
			case "/genre/18-drama/movie": s = "Drama";
			break;
			case "/genre/12-adventure/movie": s = "Adventure";
			break;	
			case "/genre/36-history/movie": s = "History";
			break;
			case "/genre/28-action/movie": s = "Action";
			break;	
			case "/genre/10749-romance/movie": s = "Romance";
			break;	
			case "/genre/878-science-fiction/movie": s = "Science-fiction";
			break;	
			case "/genre/10402-music/movie": s = "Music";
			break;	
			case "/genre/14-fantasy/movie": s = "Fantasy";
			break;	
			case "/genre/35-comedy/movie": s = "Comedy";
			break;
			default: s = "unknown genre";
			}
			list.add(s);
		}
		
		Movie m = new Movie(title, elementTime.text(), list, url + l);
		this.allMovies.add(m);	
		
		//test
		m.provideMovieDetails();
	}
}
