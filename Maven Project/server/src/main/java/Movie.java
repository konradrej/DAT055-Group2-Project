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
    private String url;

    public Movie(String title, ArrayList<String> genre, String length, String url) {
    	this.title = title;
    	this.genre = genre;
    	this.length = length;
    	this.url = url;
    }
    
    public String getTitle() {
        return title;
    }

    public ArrayList<String> getGenre() {
        return this.genre;
    }

    public String getLenght() {
        return length;
    }
    
    public String getURL() {
    	return this.url;
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

    