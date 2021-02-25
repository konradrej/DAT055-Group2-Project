import client.ClientHandler;
import server.GetBookingsBySSNCommand;
import server.GetCustomerBySSNCommand;
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
    private Collection<Booking> bookings;
    private Customer customer;

    public void updateMovies(){
        SocketClientCommunication.getInstance().sendCommand(new GetMoviesCommand());
    }

    public void updateShows(Movie movie){
        SocketClientCommunication.getInstance().sendCommand(new GetShowsByMovieCommand(movie));
    }

    public void updateCustomer(String customer){
        SocketClientCommunication.getInstance().sendCommand(new GetCustomerBySSNCommand(customer));
    }

    public void updateBookings(Booking booking){
        SocketClientCommunication.getInstance().sendCommand(new GetBookingsBySSNCommand(booking.getCustomer().getSSN()));
    }


    public MovieCollection getMovieCollection(){
        return movieCollection;
    }

    public ShowCollection getShowCollection(){
        return showCollection;
    }

    public Collection<Booking>  getBookingCollection(){
        return bookings;
    }

    public Customer  getCustomer(){
        return customer;
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

            for (Object s : seatsByShow) {
                seatsByShow1.add((Seat) s);
            }

            this.seatsByShow = seatsByShow1;
            notifyObservers();
        } catch (ClassCastException e) {
            System.err.println("Class could not be casted. Message: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void setBookings(Collection<Object> bookings)
    {
        try {
            Collection<Booking> bookings1 = new ArrayList<>();

            for(Object b : bookings)
            {
                bookings1.add((Booking)b);
            }

            this.bookings = bookings1;
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