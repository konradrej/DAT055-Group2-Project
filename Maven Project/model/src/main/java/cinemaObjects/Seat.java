package cinemaObjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class handles the seat placement
 * by storing seat numbers and the availability
 * of a seat.
 *
 * @author Erik Kieu
 * @version 2021-03-04
 */
public class Seat implements Serializable {

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

    /**
     * Constructor for initializing the Seat instance given another seat instance
     *
     * @param s the Seat which is to be cloned
     */
    public Seat(Seat s) {
        this.seatNumber = s.getSeatNumber();
        this.available = s.getAvailable();
    }


    private Seat cloneSeat() {
        return new Seat(this);
    }

    /**
     * Method for cloning a list of seats
     *
     * @param seat the list which is to be cloned
     * @return returns a cloned list of all input seats
     */
    public static List<Seat> cloneList(List<Seat> seat) {
        ArrayList<Seat> cl = new ArrayList<>();
        for (Seat s : seat) {
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
     * @return returns if this seat is available
     */
    public boolean getAvailable() {
        return this.available;
    }

    /**
     * Method for toggling the seat status
     *
     * @param status a boolean value representing the current status of the seat
     */
    public void updateSeatStatus(boolean status) {
        this.available = status;
    }

}