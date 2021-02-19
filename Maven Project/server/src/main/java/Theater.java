import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;


/**
 * @author Anthon Lenander, Erik Kieu, Phong Nguyen
 * @version version 0.0.0
 */
public class Theater implements Serializable{

	private static final long serialVersionUID = 264852408018552280L;
    private int theaterNumber;
    private boolean status;
    private Collection<Row> allRows;

    /**
     * Constructor for initializing the Theatre instance
     *
     * @param theaterNumber the number of the theatre
     * @param allRows       the Collection of allrows
     */
    public Theater(int theaterNumber, Collection<Row> allRows, Boolean status) {
        this.theaterNumber = theaterNumber;
        this.allRows = allRows;
        this.status = status;
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
    public Collection<Row> getCollectionOfRows() {
        return this.allRows;
    }

    /**
     * Method for creating a new row and adding it
     * to the collection of rows
     */
    /*
    // TODO are rowNumber and numOfSeats their own classes or should these be ints?
    public void addRow(int rowNumber, int numOfSeats) {
        Row row = new Row(r, s);
        this.allRows.add(row);
    }
    */

    public void setStatus(Boolean b) {
        this.status = b;
    }

    public boolean getStatus(){
        return this.status;
    }
}
