import java.io.Serializable;
import java.util.Collection;

import src.Row


/**
 * @author Anthon Lenander, Erik Kieu, Phong Nguyen
 * @version version 0.0.0
 */
public class Theater implements Serializable{

	
	private static final long serialVersionUID = 264852408018552280L;
    private int theaterNumber;
    private Collection<Row> allRows;

    /**
     * Constructor for initializing the Theatre instance
     *
     * @param theatreNumber the number of the theatre
     * @param allRows       the Collection of allrows
     */
    public Theater(int theaterNumber, Collection<Row> allRows) {
        this.theaterNumber = theaterNumber;
        this.allRows = allRows;
    }

    /**
     * Method for setting a theatre number
     */
    public void setTheaterNumber(int theaterNumber) {
        this.theaterNumber = theaterNumber;
    }


    /**
     * Method for getting a theatre
     *
     * @return returns the theatre instance itself
     */
    public Theatre getTheatre() {
        return this;
    }


    /**
     * Method for getting a theatre number
     *
     * @return returns the theatre instance itself
     */
    public int getTheatreNumber() {
        return this.theaterNumber;

    }


    /**
     * Method for getting a row from a collection of rows
     */
    public Collection<Row> getCollectionOfRows() {
        return this.allRows;

    }

    /**
     * Method for creating a new row and adding it
     * to the collection of rows
     */
    public void addRow(rowNumber r, seatNumber s) {
        Row r = new Row(r, s);
        this.allRows.add(r);
    }

    /**
     * Method for finding all available seats
     */
    public void availableSeats() {
        LinkedList<Seat> availableSeats = new LinkedList<Seat>();
        for (Row r : this.allRows) {
            availableSeats.addAll(r.getCollectionOfAvailableSeats);
        }
        return availableSeats;
    }


}
