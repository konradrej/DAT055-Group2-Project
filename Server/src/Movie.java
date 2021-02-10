/**
* @author Anthon Lenander, Erik Kieu, Phong Nguyen
* @version version 0.0.0
*/
public class Movie {

    private String title;
    private String genre;
    private int lenght;

    
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

    