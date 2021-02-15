public class ClientModel extends AbstractObservable {
    private static ClientModel INSTANCE;

    private ClientModel() { }

    public synchronized static ClientModel getInstance(){
        if(INSTANCE == null){
            INSTANCE = new ClientModel();
        }

        return INSTANCE;
    }

    private MovieCollection movieCollection;
    private ShowCollection showCollection;

    public void updateMovies(){
        // Call communication to send request and do something.
    }

    public void updateShows(Movie movie){
        // Call communication to send request and do something.
    }

    public MovieCollection getMovieCollection(){
        return movieCollection;
    }

    public ShowCollection getShowCollection(){
        return showCollection;
    }

    public void setMovieCollection(MovieCollection movieCollection){
        this.movieCollection = movieCollection;
        notifyObservers("movieCollection", this.movieCollection);
    }

    public void setShowCollection(ShowCollection showCollection){
        this.showCollection = showCollection;
        notifyObservers("showCollection", this.showCollection);
    }
}
