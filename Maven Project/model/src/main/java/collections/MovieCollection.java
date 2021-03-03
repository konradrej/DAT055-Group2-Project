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
 * This class handles the collection of movie provided from URL and may provide these.
 *
 * @author Phong Nguyen
 * @version 2021-03-02
 */

public class MovieCollection extends AbstractCollection {

    private static final long serialVersionUID = -6417208744468074004L;
    private final List<Movie> allMovies;
    private final String filename;
    private final String url = "https://www.themoviedb.org";

    /**
     * Constructor for initializing the MovieCollection instance
     *
     * @param filename Filename of the object when serializing
     */

    public MovieCollection(String filename) {
        this.filename = filename;
        allMovies = new ArrayList<>();
    }

    /**
     * Get method for the name of the file
     *
     * @return a string name of the object when serializing
     */

    public String getFilename() {
        return this.filename;
    }

    /**
     * Get method for the list of the movies
     *
     * @return a collection of Movies of the objects movie collection
     */

    public List<Movie> getAllMovies() {
        return this.allMovies;
    }

    /**
     * @param title movie title
     * @return a movie with matching title
     */

    public Movie getMovieByTitle(String title) {
        for (Movie movie : allMovies) {
            if (movie.getTitle().equals(title)) {
                return movie;
            }
        }
        return null;
    }

    /**
     * Scans and updates new movies to the list from this objects URL
     */

    public void scanNewMovies() {
        Document doc;
        try {
            doc = Jsoup.connect(url + "/movie/now-playing").timeout(6000).get();
        } catch (IOException e) {
            System.err.println("Error at the connection: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        Elements body = doc.select("div.page_wrapper");
        for (Element e : body.select("h2 a")) {
            String link = e.attributes().get("href");
            String title = e.attributes().get("title");
            boolean scanThis = true;
            for (Movie m : this.allMovies) {
                if (m.getURL().equals(url + link)) {
                    scanThis = false;
                    break;
                }
            }
            if (scanThis) {
                scanMovieDetails(link, title);
            }
        }
    }

    /**
     * Scans and adds the movie details: title, length, genres and URL
     *
     * @param l     - path to the movie link
     * @param title - title of the movie being added
     */

    private void scanMovieDetails(String l, String title) {
        Document doc;

        try {
            doc = Jsoup.connect(url + l).timeout(6000).get();
        } catch (IOException e) {
            System.out.println("Error at the connection: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        Elements elementTime = doc.getElementsByClass("runtime");
        Elements elementGenres = doc.select("span.genres");
        List<String> list = new ArrayList<>();
        for (Element genre : elementGenres.select("a")) {
            String s;

            switch (genre.attributes().get("href")) {

                case "/genre/53-thriller/movie":
                    s = "Thriller";
                    break;
                case "/genre/80-crime/movie":
                    s = "Crime";
                    break;
                case "/genre/27-horror/movie":
                    s = "Horror";
                    break;
                case "/genre/18-drama/movie":
                    s = "Drama";
                    break;
                case "/genre/12-adventure/movie":
                    s = "Adventure";
                    break;
                case "/genre/36-history/movie":
                    s = "History";
                    break;
                case "/genre/28-action/movie":
                    s = "Action";
                    break;
                case "/genre/10749-romance/movie":
                    s = "Romance";
                    break;
                case "/genre/878-science-fiction/movie":
                    s = "Science-fiction";
                    break;
                case "/genre/10402-music/movie":
                    s = "Music";
                    break;
                case "/genre/14-fantasy/movie":
                    s = "Fantasy";
                    break;
                case "/genre/35-comedy/movie":
                    s = "Comedy";
                    break;
                default:
                    s = "unknown genre";
            }
            list.add(s);
        }

        Movie m = new Movie(title, list, elementTime.text(), url + l);
        this.allMovies.add(m);
    }

    /**
     * Read serialized file from the filenames path
     *
     * @return MovieCollection of the read file
     */

    public MovieCollection readCollection() {
        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(this.filename))) {

            MovieCollection readThis = (MovieCollection) stream.readObject();
            System.out.println("File: " + this.filename + " has been read");
            return readThis;
        } catch (ClassCastException | ClassNotFoundException | FileNotFoundException | NullPointerException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return new MovieCollection(this.filename);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

}
