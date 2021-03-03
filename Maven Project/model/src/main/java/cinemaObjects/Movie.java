package cinemaObjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class handles the movie for a show
 *
 * @author Erik Kieu
 * @version 2021-03-02
 */
public class Movie implements Serializable {

    private static final long serialVersionUID = 1239018110549344321L;
    private final String title;
    private final List<String> genre;
    private final String length;
    private final String url;

    /**
     * Constructor when initializing Movie class
     *
     * @param title  the title of the movie
     * @param genre  all the genres of the movie
     * @param length the length of the movie
     * @param url    the URL of the movie
     */
    public Movie(String title, List<String> genre, String length, String url) {
        this.title = title;
        this.genre = genre;
        this.length = length;
        this.url = url;
    }

    /**
     * Get method to retrieve the title of the movie
     *
     * @return a string of the title
     */
    public String getTitle() {
        return title;
    }


    /**
     * Get method to retrieve the URL ouf the movie
     *
     * @return a string of the URL
     */
    public String getURL() {
        return this.url;
    }



}