import client.ClientHandler;
import server.GetMoviesCommand;
import server.GetShowsByMovieCommand;

import java.util.ArrayList;
import java.util.Collection;
import ObserverPattern.*;
import collections.*;
import cinemaObjects.*;

public class ClientModel implements ClientHandler, IObservable<ClientModel> {
    private static ClientModel INSTANCE;

    private ClientModel() { }

    public synchronized static ClientModel getInstance(){
        if(INSTANCE == null){
            INSTANCE = new ClientModel();
        }

        return INSTANCE;
    }

    private final Collection<IObserver<ClientModel>> observers = new ArrayList<>();
    private MovieCollection movieCollection;
    private ShowCollection showCollection;
    private Collection<Seat> seatsByShow;
    private Collection<Booking> bookingsBySSN;
    private Customer customer;


    public void updateMovies(){
        SocketClientCommunication.getInstance().sendCommand(new GetMoviesCommand());
    }

    public void updateShows(Movie movie){
        SocketClientCommunication.getInstance().sendCommand(new GetShowsByMovieCommand(movie));
    }

    public MovieCollection getMovieCollection(){
        return movieCollection;
    }

    public ShowCollection getShowCollection(){
        return showCollection;
    }

    public void setMovieCollection(Object movieCollection){
        try {
            this.movieCollection = (MovieCollection) movieCollection;
            notifyObservers();
        } catch (ClassCastException e){
            System.err.println("Class could not be casted. Message: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void setShowCollection(Object showCollection){
        try {
            this.showCollection = (ShowCollection) showCollection;
            notifyObservers();
        } catch (ClassCastException e){
            System.err.println("Class could not be casted. Message: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void setSeatsByShow(Collection<Object> seatsByShow) {
        try {
            Collection<Seat> seatsByShow1 = new ArrayList<>();

            for(Object s : seatsByShow)
            {
                seatsByShow1.add((Seat)s);
            }

            this.seatsByShow = seatsByShow1;
            notifyObservers();
        } catch (ClassCastException e) {
            System.err.println("Class could not be casted. Message: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void setBookingsBySSN(Collection<Object> bookingsBySSN)
    {
        try {
            Collection<Booking> bookingsBySSN1 = new ArrayList<>();

            for(Object b : bookingsBySSN)
            {
                bookingsBySSN1.add((Booking)b);
            }

            this.bookingsBySSN = bookingsBySSN1;
            notifyObservers();
        } catch (ClassCastException e) {
            System.err.println("Class could not be casted. Message: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void setBookingsByPhoneNumber(Collection<Object> bookingsByPhoneNumber)
    {
        try {
            Collection<Booking> bookingsByPhoneNumber1 = new ArrayList<>();

            for(Object b : bookingsByPhoneNumber)
            {
                bookingsByPhoneNumber1.add((Booking)b);
            }

            this.bookingsBySSN = bookingsByPhoneNumber1;
            notifyObservers();
        } catch (ClassCastException e) {
            System.err.println("Class could not be casted. Message: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void setCustomerBySSN(Object customer)
    {
        try {
            this.customer = (Customer)customer;
            notifyObservers();
        } catch (ClassCastException e) {
            System.err.println("Class could not be casted. Message: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Method for getting all seats by show
     *
     * @param show 	        the specific show for which to find all seats for
     * @return allSeats     a collection of all seats for the specified show
     */
    public Collection<Seat> getAllSeatsByShow(Show show)
    {
        Collection<Seat> allSeats = null;

        for(Show s : this.showCollection.getAllShows())
        {
            if(s.equals(show)) allSeats = s.getAllSeats();
        }

        return allSeats;
    }

    @Override
    public void addObserver(IObserver<ClientModel> obs) {
        observers.add(obs);
    }

    @Override
    public void removeObserver(IObserver<ClientModel> obs) {
        observers.remove(obs);
    }

    public void notifyObservers() {
        for(IObserver<ClientModel> o : observers) {
            o.notify(this);
        }
    }
}