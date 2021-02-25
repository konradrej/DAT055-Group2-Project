package cinemaObjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Anthon Lenander, Erik Kieu, Phong Nguyen
 * @version version 0.0.0
 */
public class Theater implements Serializable{

	private static final long serialVersionUID = 264852408018552280L;
    private int theaterNumber;
    private List<Row> allRows;

    /**
     * Constructor for initializing the Theatre instance
     *
     * @param theaterNumber the number of the theatre
     * @param allRows       the Collection of allrows
     */

    public Theater(int theaterNumber, List<Row> allRows) {
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
    public Theater getTheater() {
        return this;
    }


    /**
     * Method for getting a theatre number
     *
     * @return returns the theatre instance itself
     */

    public int getTheaterNumber() {
        return this.theaterNumber;
    }

    /**
     * Method for getting a row from a collection of rows
     */
    public List<Row> getCollectionOfRows() {
        return this.allRows;
    }


    /*
    // TODO DO WE NEED THIS? are rowNumber and numOfSeats their own classes or should these be ints?
    public void addRow(int rowNumber, int numOfSeats) {
        cinemaObjects.Row row = new cinemaObjects.Row(r, s);
        this.allRows.add(row);
    }
    */

}
