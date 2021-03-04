package cinemaObjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * This class handles the theater and contains
 * a list of rows and theater number to
 * differentiate the multiple theaters in a cinema.
 *
 * @author Erik Kieu
 * @version 2021-03-04
 */
public class Theater implements Serializable {

    private static final long serialVersionUID = 264852408018552280L;
    private int theaterNumber;
    private List<Row> allRows;

    /**
     * Constructor for initializing the Theater instance
     *
     * @param theaterNumber the number of the theater
     * @param allRows       the list of allrows
     */
    public Theater(int theaterNumber, List<Row> allRows) {
        this.theaterNumber = theaterNumber;
        this.allRows = Row.cloneList(allRows);
    }

    /**
     * Constructor for initializing the Theater instance
     *
     * @param t the number of the theater

     */
    public Theater(Theater t) {
        this.theaterNumber = t.theaterNumber;
        this.allRows = Row.cloneList(t.getAllRows());
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
     * Method for getting a theatre number
     *
     * @return returns the theatre instance itself
     */
    public int getTheaterNumber() {
        return this.theaterNumber;
    }

    /**
     * Method for getting a row from a list of rows
     *
     * @return returns this theaters list of rows as a list of rows
     */
    public List<Row> getAllRows() {
        return this.allRows;
    }


}
