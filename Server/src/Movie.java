import java.io.Serializable;

/**
* @author Anthon Lenander, Erik Kieu, Phong Nguyen
* @version version 0.0.0
*/
public class Movie implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
    private String genre;
    private int lenght;

    public Movie(String t, String g, int l) {
    	this.title = t;
    	this.genre = g;
    	this.lenght = l;
    }
    
    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getMovieLenght() {
        return lenght;
    }

    public void provideMovieDetails(){
        System.out.println(title);
        System.out.println(lenght);
        System.out.println(genre);
    }
}

    