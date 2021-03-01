import client.ClientHandler;
import misc.ResponseStatus;
import server.*;

import java.util.ArrayList;
import java.util.List;

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

    private Navigator navigator;

    public void setNavigator(Navigator navigator){
        this.navigator = navigator;
    }

    public Navigator getNavigator(){
        return navigator;
    }

    private final ArrayList<IObserver<ClientModel>> observers = new ArrayList<>();
    private MovieCollection movieCollection;
    private ShowCollection showCollection;
    private ArrayList<Booking> bookings;
    private Customer customer;
    private ResponseStatus response;

    public void updateMovies(){
        SocketClientCommunication.getInstance().sendCommand(new GetMoviesCommand());
    }

    public void updateShows(Movie movie){
        SocketClientCommunication.getInstance().sendCommand(new GetShowsByMovieCommand(movie));
    }

    public void updateCustomer(String customer){
        SocketClientCommunication.getInstance().sendCommand(new GetCustomerBySSNCommand(customer));
    }

    public void updateBookingsBySSN(String SSN){
        SocketClientCommunication.getInstance().sendCommand(new GetBookingsBySSNCommand(SSN));
    }

    public void updateBookingsByPhoneNumber(String phoneNumber){
        SocketClientCommunication.getInstance().sendCommand(new GetBookingsByPhoneNumberCommand(phoneNumber));
    }

    public void createBooking(Show show, Customer customer, List<Row> rows){
        SocketClientCommunication.getInstance().sendCommand(new CreateBookingCommand(show, customer, new ArrayList<>(rows)));
    }

    public MovieCollection getMovieCollection(){
        return movieCollection;
    }

    public ShowCollection getShowCollection(){
        return showCollection;
    }

    public ArrayList<Booking> getBookingCollection(){
        return bookings;
    }

    public Customer getCustomer(){
        return customer;
    }
    public ResponseStatus getResponse(){
        return response;
    }

    @Override
    public void setMovieCollection(Object movieCollection){
        try {
            this.movieCollection = (MovieCollection) movieCollection;
            notifyObservers();
        } catch (ClassCastException e){
            System.err.println("Class could not be casted. Message: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void setShowCollection(Object showCollection){
        try {
            this.showCollection = (ShowCollection) showCollection;
            notifyObservers();
        } catch (ClassCastException e){
            System.err.println("Class could not be casted. Message: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void setBookings(ArrayList<Object> bookings)
    {
        try {
            ArrayList<Booking> bookings1 = new ArrayList<>();

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

    @Override
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

    @Override
    public void setResponseStatus(ResponseStatus response) {
        this.response = response;
        notifyObservers();
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