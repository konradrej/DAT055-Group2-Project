package collections;

import cinemaObjects.Movie;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
* @author Anthon Lenander, Erik Kieu, Phong Nguyen
* @version version 0.0.0
*/
public class MovieCollection extends AbstractCollection {

	private static final long serialVersionUID = -6417208744468074004L;
	private final List<Movie> allMovies;
	private final String filename;
	private final String url = "https://www.themoviedb.org";
	
	/**
	 * Constructor for initializing the collections.MovieCollection instance
	 * 
	 * @param filename	Filename of the object when serializing
	 */
	
	public MovieCollection(String filename) {
		this.filename = filename;
		allMovies = new ArrayList <>();
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
	
	public List <Movie> getAllMovies(){
		return this.allMovies;
	}

	/**
	 * @param title movie title
	 *
	 * @return a movie with matching title
	 */

	public Movie getMovieByTitle(String title){
		for(Movie movie : allMovies){
			if(movie.getTitle().equals(title)){
				return movie;
			}
		}
		return null;
	}
	
	/**
	 * Adds a movie to the m to the objects movie collection
	 * 
	 * @param m to add
	 */
	
	public void addMovie(Movie m) {
		this.allMovies.add(m);
	}
	
	/**
	 * Removes a movie to the m to the objects movie collection
	 * 
	 * @param m to remove
	 */
	
	public void removeMovie(Movie m ) {
		for(Movie m2: this.allMovies) {
			if(m2.equals(m)) {
				this.allMovies.remove(m2);
			}
		}
		System.out.println("cinemaObjects.Movie not found");
	}
	
	/**
	 * Get a collection of selected movies with title and genres perferences
	 * 
	 * @param title - selected title for the movies
	 * @param genre - selected genre for the movies
	 */
	
	public List <Movie> getSelectedMovies(String title, String genre){
		List <Movie> selectedMovies = new ArrayList<>();
		
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
	 * Scans and updates new movies to the collection from this objects URL
	 */
	
	public void scanNewMovies() {
		Document doc;
		try {
			doc = Jsoup.connect(url + "/movie/now-playing").timeout(6000).get();
		}
		catch(IOException e) {
			System.err.println("Error at the connection: " + e.getMessage());
			e.printStackTrace();
			return;
		}
		
		Elements body = doc.select("div.page_wrapper");
		for(Element e : body.select("h2 a")) {
			String link = e.attributes().get("href");
			String title = e.attributes().get("title");
			boolean scanThis = true;
			for( Movie m : this.allMovies) {
				if(m.getURL().equals(url + link)){
					scanThis = false;
					break;
				}
			}
			if(scanThis) {
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
			System.out.println("Error at the connection: " + e.getMessage());
			e.printStackTrace();
			return;
		}
		
		Elements elementTime = doc.getElementsByClass("runtime");
		Elements elementGenres = doc.select("span.genres");
		List <String> list = new ArrayList<>();
		for(Element genre : elementGenres.select("a")) {
			String s;

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
		
		Movie m = new Movie(title, list, elementTime.text(), url + l);
		this.allMovies.add(m);
	}

	/**
	 * Read serialized file
	 *
	 * @return MovieCollection of the read file
	 * @exception ClassCastException returns an empty MovieCollection
	 * @exception FileNotFoundException returns an empty MovieCollection
	 * @exception NullPointerException returns an empty MovieCollection
	 * @exception IOException returns null
	 */

	public MovieCollection readCollection()throws IOException{
		try(ObjectInputStream stream = new ObjectInputStream(new FileInputStream(this.filename))){

			MovieCollection readThis = (MovieCollection) stream.readObject();
			System.out.println("File: "  + this.filename + " has been read");
			return readThis;
		}

		catch (ClassNotFoundException | FileNotFoundException | NullPointerException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			return new MovieCollection(this.filename);
		}
		catch (IOException e ){
			System.err.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

}
