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
    public void addSeat(boolean available) {
    	
        Seat s = new Seat(this.allSeats.size() + 1, available);
        this.allSeats.add(s);
    }

    /**
     * Method for finding n number of adjacent available seats
     */
    public void adjacentAvailableSeats() {
        //TODO: Parameter
        //TODO: code to finding n number of adjacent available seats

    }

    public Collection<Seat> getAllAvailableSeats()
    {
    	Collection<Seat> availableSeats = Collections.emptyList();
    	
    	for(Seat s : this.allSeats)
    	{
    		if(s.getAvailable()) availableSeats.add(s);
    	}
    	
    	return availableSeats;
    }
    
    /**
     * Method for getting available seats from a
     * collection of available seats
     */
    public Collection<Seat> getAvailableSeats(int numOfSeats) {
        Collection<Seat> availableSeats = Collections.emptyList();
        
        if(this.getAllAvailableSeats().size() > numOfSeats)
        {
        	availableSeats = this.getAllAvailableSeats();
        	Collection<Seat> temp = Collections.emptyList();
        	
        	int count = 0;
        	
        	for(Seat s : availableSeats)
        	{
        		if(count < numOfSeats)
        		{
        			temp.add(s);
        			count++;
        		}
        	}
        	
        	availableSeats = temp;
        }
        else if(this.getAllAvailableSeats().size() == numOfSeats)
        {
        	availableSeats = this.getAllAvailableSeats();
        }
        
        return availableSeats;
    }
    
    
    /**
     * Method for getting n number of adjacent available seats
     * Return information is missing
     */
    
    public Collection<Seat> getAdjacentAvailableSeats (int numOfSeats){
    	Collection<Seat> availableSeatsOfRow = this.getAvailableSeats(numOfSeats);
    	Collection<Seat> availableAdjacentSeats = Collections.emptyList();
		
		Seat[] availableSeatsOfRowArray = (Seat[])availableSeatsOfRow.toArray();
		Seat[] availableAdjacentSeatsOfRowArray = new Seat[numOfSeats];
		
		for(int i = 0; i < availableSeatsOfRowArray.length - numOfSeats; i++)
		{
			boolean availableAdjacent = true;
			
			for(int j = i; j <= numOfSeats; j++)
			{
				if(!availableSeatsOfRowArray[j].getAvailable())
				{
					availableAdjacent = false;
					break;
				}
				else availableAdjacentSeatsOfRowArray[j] = availableSeatsOfRowArray[i];
			}
			
			if(availableAdjacent)
			{
				for(int j = 0; j < numOfSeats; j++)
				{
					availableAdjacentSeats.add(availableAdjacentSeatsOfRowArray[j]);
				}
			}
		}
		
		return availableAdjacentSeats;
    }
}
