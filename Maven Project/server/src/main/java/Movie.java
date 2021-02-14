import java.io.Serializable;
import java.util.ArrayList;

/**
* @author Anthon Lenander, Erik Kieu, Phong Nguyen
* @version version 0.0.0
*/
public class Movie implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1239018110549344321L;
	private String title;
    private ArrayList <String> genre ;
    private String length;
    private String URL;

    public Movie(String t, String l, ArrayList <String> g, String u) {
    	this.title = t;
    	this.genre = g;
    	this.length = l;
    	this.URL = u;
    }
    
    public String getTitle() {
        return title;
    }

    public ArrayList <String> getGenre() {
        return this.genre;
    }

    public String getLenght() {
        return length;
    }
    
    public String getURL() {
    	return this.URL;
    }

    public void provideMovieDetails(){
        System.out.println(title);
        System.out.println(length);
        for(String s : this.genre) {
        	System.out.print(s + " ");
        }
        System.out.println();
    }
}

    