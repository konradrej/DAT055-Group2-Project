package app;

import client.ClientHandler;
import misc.ResponseStatus;
import server.*;

import java.util.ArrayList;
import java.util.List;

import util.*;
import collections.*;
import cinemaObjects.*;

/**
 *  This singleton class recieves all data from the server.
 *
 * @author Jakob Ståhl
 * @version 2021-03-08
 */
public enum ClientModel implements ClientHandler, IObservable<ClientModel> {
    INSTANCE;

    /**
     * Get method to get the instance of ClientModel object
     *
     * @return a ClientModel of the instance
     */
    public static ClientModel getInstance() {
        return INSTANCE;
    }


    private Navigator navigator;

    /**
     * Method for setting Navigator .
     * @param navigator. Parameter for navigator.
     */
    public void setNavigator(Navigator navigator) {
        this.navigator = navigator;
    }

    /**
     * Method for getting Navigator .
     *
     * @return variable navigator.
     */
    public Navigator getNavigator() {
        return navigator;
    }

    private final ArrayList<IObserver<ClientModel>> observers = new ArrayList<>();
    private MovieCollection movieCollection;
    private ShowCollection showCollection;
    private List<Booking> bookings;
    private Customer customer;
    private ResponseStatus response;
    private boolean connectionAlive = false;

    /**
     * Updates the MovieCollection in the server
     * through server command GetMoviesCommand.
     */
    public void updateMovies() {
        SocketClientCommunication.getInstance().sendCommand(new GetMoviesCommand());
    }

    /**
     * Updates the ShowCollection in the server
     * through server command GetShowsByMovieDateRangeCommand.
     *
     * @param movie     Movie of which shows should update
     * @param startDate Start date of the interval of shows that's updated.
     * @param endDate   End date of the interval of shows that's updated.
     */
    public void updateShows(Movie movie, CinemaDate startDate, CinemaDate endDate) {
        SocketClientCommunication.getInstance().sendCommand(new GetShowsByMovieDateRangeCommand(movie, startDate, endDate));
    }

    /**
     * Updates the CustomerCollection in the server
     * through server command GetCustomerBySSNCommand.
     *
     * @param SSN String input of Customers SSN (Social security number).
     */
    public void updateCustomer(String SSN) {
        SocketClientCommunication.getInstance().sendCommand(new GetCustomerBySSNCommand(SSN));
    }

    /**
     * Updates the BookingCollection by customers SSN
     * through server command GetBookingsBySSNCommand.
     *
     * @param SSN String input of Customers SSN (Social security number).
     */
    public void updateBookingsBySSN(String SSN) {
        SocketClientCommunication.getInstance().sendCommand(new GetBookingsBySSNCommand(SSN));
    }

    /**
     * Updates the BookingsByPhoneNumber in the server
     * through server command GetBookingsByPhoneNumberCommand.
     *
     * @param phoneNumber Customers phone number.
     */
    public void updateBookingsByPhoneNumber(String phoneNumber) {
        SocketClientCommunication.getInstance().sendCommand(new GetBookingsByPhoneNumberCommand(phoneNumber));
    }

    /**
     * Creates a booking through server
     * command CreateBookingCommand.
     *
     * @param show     Show that is booked.
     * @param customer The customer that is booking.
     * @param rows     List of rows in theater.
     */
    public void createBooking(Show show, Customer customer, List<Row> rows) {
        SocketClientCommunication.getInstance().sendCommand(new CreateBookingCommand(show, customer, new ArrayList<>(rows)));
    }

    /**
     * Cancels a booking through server
     * command CancelBookingCommand.
     *
     * @param booking Booking that is canceled.
     */
    public void cancelBooking(Booking booking) {
        SocketClientCommunication.getInstance().sendCommand(new CancelBookingCommand(booking));
    }

    /**
     * Method to get all movies in movieCollection.
     *
     * @return movieCollection
     */
    public MovieCollection getMovieCollection() {
        return movieCollection;
    }

    /**
     * Method to get all shows in showCollection.
     *
     * @return showCollection
     */
    public ShowCollection getShowCollection() {
        return showCollection;
    }

    /**
     * Method to get all bookings from bookingCollection
     *
     * @return bookings
     */
    public List<Booking> getBookingCollection() {
        return bookings;
    }

    /**
     * Method to get customer.
     *
     * @return customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Method to get the ResponseStatus.
     *
     * @return response
     */
    public ResponseStatus getResponse() {
        return response;
    }

    /**
     * Boolean method to see if connection is running properly.
     *
     * @return connectionAlive
     */
    public boolean getConnectionAlive() {
        return connectionAlive;
    }

    /**
     * Boolean method to setConnectionAlive and notifies observers.
     * Notifies all observers.
     *
     * @param connectionAlive Boolean parameter.
     */
    public void setConnectionAlive(boolean connectionAlive) {
        this.connectionAlive = connectionAlive;
        notifyObservers();
    }

    /**
     * Method to set movieCollection to the movieCollection-parameter.
     * It sets showCollection to null and notifies observers.
     *
     * @param movieCollection Parameter of class movieCollection
     */
    @Override
    public void setMovieCollection(MovieCollection movieCollection) {
        this.movieCollection = movieCollection;
        this.showCollection = null;
        notifyObservers();
    }

    /**
     * Method to set showCollection to the showCollection-parameter and notify observers.
     *
     * @param showCollection Parameter of class movieCollection.
     */
    @Override
    public void setShowCollection(ShowCollection showCollection) {
        this.showCollection = showCollection;
        notifyObservers();
    }

    /**
     * Method to set the List of bookings bookings-parameter and notify observers.
     *
     * @param bookings List of bookings.
     */
    @Override
    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
        notifyObservers();
    }

    /**
     * Method to set Customer by it's SSN and notify observers.
     *
     * @param customer Parameter of type Customer.
     */
    @Override
    public void setCustomerBySSN(Customer customer) {
        this.customer = customer;
        notifyObservers();
    }

    /**
     * Method to set responseStatus and notify observers.
     *
     * @param response Parameter of type ResponseStatus.
     */
    @Override
    public void setResponseStatus(ResponseStatus response) {
        this.response = response;
        notifyObservers();
    }

    /**
     * Method to add observer.
     *
     * @param obs object which observes.
     */
    @Override
    public void addObserver(IObserver<ClientModel> obs) {
        observers.add(obs);
    }

    /**
     * Method to remove observer.
     *
     * @param obs object to remove.
     */
    @Override
    public void removeObserver(IObserver<ClientModel> obs) {
        observers.remove(obs);
    }

    /**
     * Notifies all observers.
     */
    public void notifyObservers() {
        for (IObserver<ClientModel> o : observers) {
            o.notify(this);
        }
    }
}