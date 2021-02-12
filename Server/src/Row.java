import java.util.Collection;

/**
 * @author Anthon Lenander, Erik Kieu, Phong Nguyen
 * @version version 0.0.0
 */
public class Row {

    private int rowNumber;
    private int seatNumber;
    private Collection <Seat> allSeats;

    /**
     * Constructor for initializing the Row instance
     *
     * @param rowNumber      the number of the row
     * @param seatNumber     the number of the seat
     */
    public Row(int rowNumber, int seatNumber,...){
        //TODO: row parameter
    }

    /**
     * Method for setting a row number
     */
    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    /**
     * Method for getting a row
     *
     * @return      returns the Row instance itself
     */
    public Row getRowNumber() {
        return this;
    }


    /**
     * Method for getting seats from a collection of seats
     */
    public Collection <Seat> getCollectionOfSeats() {
        //TODO: return information
    }

    /**
     * Method for creating new seats and adding it to
     * a collection of seats
     */
    public void addSeat(int seatNumber, boolean available){
        Seat s = new Seat(seatNumber, available);
        this.allSeats.add(s);
    }

    /**
     * Method for finding n number of adjacent available seats
     */
    public void adjacentAvailableSeats() {
        //TODO: Parameter
        //TODO: code to finding n number of adjacent available seats

    }

    /**
     * Method for getting n number of adjacent available seats
     * Return information is missing
     */
    public void getAdjacentAvailableSeats() {
        //TODO: return information
    }

    /**
     * Method for getting available seats from a
     * collection of available seats
     */
    public void getCollectionOfAvailableSeats() {

    }

}
