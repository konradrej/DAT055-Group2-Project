package cinemaObjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class handles rows for the seating placement
 *
 * @author Erik Kieu
 * @version 2021-03-02
 */
public class Row implements Serializable {

    private static final long serialVersionUID = -1432188689020345890L;
    private int rowNumber;
    private List<Seat> allSeats;

    /**
     * Constructor for initializing the cinemaObjects.Row instance
     *
     * @param rowNumber the number of the row
     * @param allSeats  the Collection of allSeats
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
     * Method for setting a row number
     *
     * @param rowNumber the row number to set this rows rowNumber to
     */
    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    /**
     * Method for getting a row
     *
     * @return returns the cinemaObjects.Row instance itself
     */
    public Row cloneRow() {
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
     * Method for getting seats from a collection of seats
     *
     * @return returns an arraylist containing all seats of this Row instance's seats
     */
    public List<Seat> getAllSeats() {
        return new ArrayList<>(this.allSeats);
    }

    /**
     * Method for creating new seats and adding it to a collection of seats
     *
     * @param available a boolean value signifying if the seat to be added should be made available or not
     */
    public void addSeat(boolean available) {

        Seat s = new Seat(this.allSeats.size() + 1);
        this.allSeats.add(s);
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

    /**
     * Method for getting available seats from a collection of available seats
     *
     * @param numOfSeats the number of available seats to find
     * @return returns an arraylist of size numOfSeats of available seats
     */
    public ArrayList<Seat> getAvailableSeats(int numOfSeats) {
        ArrayList<Seat> availableSeats = new ArrayList<>();

        if (this.getAllAvailableSeats().size() > numOfSeats) {
            availableSeats = this.getAllAvailableSeats();
            ArrayList<Seat> temp = new ArrayList<>();

            int count = 0;

            for (Seat s : availableSeats) {
                if (count < numOfSeats) {
                    temp.add(s);
                    count++;
                }
            }

            availableSeats = temp;
        } else if (this.getAllAvailableSeats().size() == numOfSeats) {
            availableSeats = this.getAllAvailableSeats();
        } else if (this.getAllAvailableSeats().size() < numOfSeats) availableSeats = new ArrayList<>();

        return availableSeats;
    }


    /**
     * Method for getting n number of adjacent available seats
     *
     * @param numOfSeats the number of adjacent available seats to find
     * @return returns a list of size numOfSeats if numOfSeats adjacent available seats are found
     */
    public ArrayList<Seat> getAdjacentAvailableSeats(int numOfSeats) {
        ArrayList<Seat> availableSeatsOfRow = this.getAvailableSeats(numOfSeats);

        Seat[] availableSeatsOfRowArray = (Seat[]) availableSeatsOfRow.toArray();
        Seat[] availableAdjacentSeatsOfRowArray = new Seat[numOfSeats];

        for (int i = 0; i < availableSeatsOfRowArray.length - numOfSeats; i++) {
            boolean availableAdjacent = true;

            for (int j = i; j <= numOfSeats; j++) {
                if (!availableSeatsOfRowArray[j].getAvailable()) {
                    availableAdjacent = false;
                    break;
                } else availableAdjacentSeatsOfRowArray[j] = availableSeatsOfRowArray[i];
            }

            if (availableAdjacent) {
                return new ArrayList<>(Arrays.asList(availableAdjacentSeatsOfRowArray).subList(0, numOfSeats));
            } else {
                availableAdjacentSeatsOfRowArray = new Seat[numOfSeats];
            }
        }

        return new ArrayList<>();
    }

}