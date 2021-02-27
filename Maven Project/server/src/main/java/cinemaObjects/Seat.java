package cinemaObjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Anthon Lenander, Erik Kieu, Phong Nguyen
 * @version version 0.0.0
 */
public class Seat implements Serializable{

	private static final long serialVersionUID = 2724216208606386504L;
	private final int seatNumber;
    private boolean available;

    /**
     * Constructor for initializing the cinemaObjects.Seat instance
     *
     * @param seatNumber the number of the seat
     */
    public Seat(int seatNumber) {
        this.seatNumber = seatNumber;
        this.available = true;
    }

    public Seat (Seat s){
        this.seatNumber = s.seatNumber;
        this.available = true;
    }

    /**
     * Method for getting the details of the seat
     *
     * @return returns the seat instance itself
     */
    public Seat cloneSeat() {
        return new Seat(this);
    }

   public static List <Seat>cloneList (List<Seat> seat){
        ArrayList<Seat> cl = new ArrayList<>();
        for(Seat s: seat){
            cl.add(s.cloneSeat());
        }
        return cl;
    }

    /**
     * Method for getting the seat number
     *
     * @return returns the seat number
     */
    public int getSeatNumber() {
        return this.seatNumber;
    }

    /**
     * Method for getting an available seat
     *
     * @return      returns if seat is available
     */
    public boolean getAvailable() {
        return this.available;
    }

    /**
     * Method for toggling the seat status
     */
    public void updateSeatStatus(boolean status) {
        this.available = status;
    }


}
