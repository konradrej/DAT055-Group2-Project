package cinemaObjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * This class handles the theater and contains
 * a collection of rows and theater number to
 * differentiate the multiple theaters in a cinema.
 *
 * @author Erik Kieu
 * @version 2021-03-02
 */
public class Theater implements Serializable {

    private static final long serialVersionUID = 264852408018552280L;
    private int theaterNumber;
    private List<Row> allRows;

    /**
     * Constructor for initializing the Theater instance
     *
     * @param theaterNumber the number of the theater
     * @param allRows       the Collection of allrows
     */
    public Theater(int theaterNumber, List<Row> allRows) {
        this.theaterNumber = theaterNumber;
        this.allRows = Row.cloneList(allRows);
    }

    public Theater(Theater t) {
        this.theaterNumber = t.theaterNumber;
        this.allRows = Row.cloneList(t.getCollectionOfRows());
    }

    /**
     * Method for setting a theatre number
     *
     * @param theaterNumber the number to set to this theaters theaterNumber
     */
    public void setTheaterNumber(int theaterNumber) {
        this.theaterNumber = theaterNumber;
    }

    /**
     * Method for getting a theatre
     *
     * @return returns the theater instance itself
     */
    public Theater cloneTheater() {
        return new Theater(this);
    }

    /**
     * Method for cloning a list of theaters
     *
     * @param theaters the list of theaters to clone
     * @return returns a cloned list of the input list of theaters
     */
    public static List<Theater> cloneList(List<Theater> theaters) {
        List<Theater> cl = new ArrayList<>();
        for (Theater t : theaters) {
            cl.add(t.cloneTheater());
        }
        return cl;
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
     *
     * @return returns this theaters collection of rows as a list of rows
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
