import client.ClientHandler;

import java.util.Collection;
import java.util.LinkedList;

public class ClientModel extends AbstractObservable implements ClientHandler {
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

    //public ShowCollection getShowCollection(){
        //return showCollection;
    //}

    public void setMovieCollection(Object movieCollection){
        try {
            this.movieCollection = (MovieCollection) movieCollection;
            notifyObservers("movieCollection", this.movieCollection);
        } catch (ClassCastException e){
            System.err.println("Class could not be casted. Message: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void setShowCollection(Object showCollection){
        try {
            this.showCollection = (ShowCollection) showCollection;
            notifyObservers("showCollection", this.showCollection);
        } catch (ClassCastException e){
            System.err.println("Class could not be casted. Message: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
