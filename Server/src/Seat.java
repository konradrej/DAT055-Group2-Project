import java.io.Serializable;

/**
 * @author Anthon Lenander, Erik Kieu, Phong Nguyen
 * @version version 0.0.0
 */
public class Seat implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 2724216208606386504L;
	private int seatNumber;
    private boolean available;

    /**
     * Constructor for initializing the Seat instance
     *
     * @param seatNumber the number of the seat
     * @param available  the seat status
     */
    public Seat(int seatNumber, boolean available) {
        seatNumber = this.seatNumber;
        available = this.available;
    }

    /**
     * Method for getting the details of the seat
     *
     * @return returns the seat instance itself
     */
    public Seat getSeat() {
        return this;
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
     * Method for updating (setting) the seat status
     */
    public void updateSeatStatus(boolean available) {
        available = this.available;
    }


}
