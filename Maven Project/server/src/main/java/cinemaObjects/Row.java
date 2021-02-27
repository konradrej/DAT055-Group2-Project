package cinemaObjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Anthon Lenander, Erik Kieu, Phong Nguyen
 * @version version 0.0.0
 */
public class Row implements Serializable{

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

    public Row(Row r){
    	this.allSeats = Seat.cloneList(r.getAllSeats());
    	this.rowNumber = r.rowNumber;
	}

	public static List<Row> cloneList(List <Row> rows){
    	List<Row> cl = new ArrayList<>();
    	for(Row r: rows){
    		cl.add(r.cloneRow());
		}
    	return cl;
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
     */
    public List<Seat> getAllSeats() {
        return new ArrayList<>(this.allSeats);
    }

    /**
     * Method for creating new seats and adding it to
     * a collection of seats
     */
    public void addSeat(boolean available) {
    	
        Seat s = new Seat(this.allSeats.size() + 1);
        this.allSeats.add(s);
    }

	public void addSeat(Seat seat){
		this.allSeats.add(seat);
	}

	public void removeSeat(Seat seat){
		this.allSeats.remove(seat);
	}

    /**
     * Method for finding all seats
     */
    public ArrayList<Seat> getAllAvailableSeats()
    {
		ArrayList<Seat> availableSeats = new ArrayList<>();
    	
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
    public ArrayList<Seat> getAvailableSeats(int numOfSeats) {
		ArrayList<Seat> availableSeats = new ArrayList<>();
        
        if(this.getAllAvailableSeats().size() > numOfSeats)
        {
        	availableSeats = this.getAllAvailableSeats();
			ArrayList<Seat> temp = new ArrayList<>();
        	
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
    
    public ArrayList<Seat> getAdjacentAvailableSeats (int numOfSeats){
		ArrayList<Seat> availableSeatsOfRow = this.getAvailableSeats(numOfSeats);
		ArrayList<Seat> availableAdjacentSeats = new ArrayList <> ();
		
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
