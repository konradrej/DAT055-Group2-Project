import java.util.Collection;

/**
* @author Anthon Lenander, Erik Kieu, Phong Nguyen
* @version version 0.0.0
*/
public class Theater {

    private int theaterNumber;
    private Collection <Row> allRows;

    /**
     * Constructor for initializing the Theatre instance
     *
     * @param theatreNumber     the number of the theatre
     */
    public Theatre(int theaterNumber,...){
        //TODO: Theatre parameter
    }

    /**
     * Method for setting a theatre number
     * */
    public void setTheaterNumber(int theaterNumber){
        this.theaterNumber = theaterNumber;
    }

    /**
     * Method for getting a theatre number
     *
     * @return      returns the theatre instance itself
     * */
    public Theatre getTheatre(){
        return this;
    }



    /**
     * Method for getting a row from a collection of rows
     * */
    public Collection <Row> getCollectionOfRows(){
        //todo: return information

    }

    /**
     * Method for creating a new row and adding it
     * to the collection of rows
     * */
    public void addRow(rowNumber r, seatNumber s){

        Row r = new Row(r,s);
        this.allRows.add(r);
    }

    /**
     * Method for finding all available seats
     *
     *
     * */
    public void availableSeats(){
        //todo: the code for finding available seats

        //idk if this method should in this class since it
        //is similar to the method in the Row class
    }



}
