package cinemaObjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class handles rows for the seating placement
 *
 * @author Erik Kieu
 * @version 2021-03-04
 */
public class Row implements Serializable {

    private static final long serialVersionUID = -1432188689020345890L;
    private final int rowNumber;
    private final List<Seat> allSeats;

    /**
     * Constructor for initializing the cinemaObjects.Row instance
     *
     * @param rowNumber the number of the row
     * @param allSeats  the list of allSeats
     */
    public Row(int rowNumber, List<Seat> allSeats) {
        this.rowNumber = rowNumber;
        this.allSeats = Seat.cloneList(allSeats);
    }

    /**
     * Constructor for initializing the Row instance given another Row instance
     *
     * @param r the row to clone
     */
    public Row(Row r) {
        this.allSeats = Seat.cloneList(r.getAllSeats());
        this.rowNumber = r.rowNumber;
    }

    /**
     * Method for cloning a list of rows given a list of rows
     *
     * @param rows the rows to be cloned
     * @return returns a cloned list of the input list of rows
     */
    public static List<Row> cloneList(List<Row> rows) {
        List<Row> cl = new ArrayList<>();
        for (Row r : rows) {
            cl.add(r.cloneRow());
        }
        return cl;
    }

    /**
     * Method for getting a row
     *
     * @return returns the cinemaObjects.Row instance itself
     */
    private Row cloneRow() {
        return new Row(this);
    }

    /**
     * Method for getting a row number
     *
     * @return returns the row number
     */
    public int getRowNumber() {
        return rowNumber;
    }

    /**
     * Method for getting seats from a list of seats
     *
     * @return returns an arraylist containing all seats of this Row instance's seats
     */
    public List<Seat> getAllSeats() {
        return this.allSeats;
    }


    /**
     * Method for adding a seat given another instance of a seat
     *
     * @param seat the seat which is to be added
     */
    public void addSeat(Seat seat) {
        this.allSeats.add(seat);
    }

    /**
     * Method for removing a seat given another instance of a seat
     *
     * @param seat the seat which is to be removed
     */
    public void removeSeat(Seat seat) {
        this.allSeats.remove(seat);
    }

    /**
     * Method for finding all seats
     *
     * @return returns a list of all available seats
     */
    public ArrayList<Seat> getAllAvailableSeats() {
        ArrayList<Seat> availableSeats = new ArrayList<>();

        for (Seat s : this.allSeats) {
            if (s.getAvailable()) availableSeats.add(s);
        }

        return availableSeats;
    }
}