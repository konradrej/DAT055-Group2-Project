import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

/**
 * @author Anthon Lenander, Erik Kieu, Phong Nguyen
 * @version version 0.0.0
 */
public class Row implements Serializable{

	private static final long serialVersionUID = -1432188689020345890L;
	private int rowNumber;
    private Collection<Seat> allSeats;


    /**
     * Constructor for initializing the Row instance
     *
     * @param rowNumber the number of the row
     * @param allSeats  the Collection of allSeats
     */
    public Row(int rowNumber, Collection<Seat> allSeats) {
        this.rowNumber = rowNumber;
        this.allSeats = allSeats;
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
     * @return returns the Row instance itself
     */
    public Row getRow() {
        return this;
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
     */
    public Collection<Seat> getAllSeats() {
        return this.allSeats;
    }

    /**
     * Method for creating new seats and adding it to
     * a collection of seats
     */
    public void addSeat(int seatNumber, boolean available) {
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
     * Method for getting available seats from a
     * collection of available seats
     */
    public Collection<Seat> getAllAvailableSeats() {
        Collection<Seat> availableSeats = Collections.emptyList();
        for (Seat s : this.allSeats) {
            if (s.getAvailable()) {
                availableSeats.add(s);
            }
        }
        return availableSeats;
    }
    
    /**
     * Method for getting n number of adjacent available seats
     * Return information is missing
     */
    
    public Collection <Seat> getAdjacentAvailableSeats (int numOfSeats){
    	int count = 0;
    	Collection <Seat> adjacentSeats = new LinkedList <Seat>();
    	Collection <Seat> temp = new LinkedList <Seat>();
    	for(Seat s : this.allSeats) {
    		if(s.getAvailable()) {
    			count++;
    			temp.add(s);
    		}
    		else {
    			if(count >= numOfSeats) {
    				adjacentSeats.addAll(temp);
    			}
    			temp.removeAll(temp);
    			count = 0;
    		}
    	}
    	return adjacentSeats;
    }

}
