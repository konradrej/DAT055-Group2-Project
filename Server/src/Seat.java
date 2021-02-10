/**
* @author Anthon Lenander, Erik Kieu, Phong Nguyen
* @version version 0.0.0
*/
public class Seat {

    private int seatNumber;
    private boolean available;

    /**
     * Store detail (seat number)
     */
    public void setSeatNumber(int seatNumber){
        this.seatNumber = seatNumber;
    }

    /**
     * Provide detail (seat number)
     */
    public void getSeatNumber(){
        return seatNumber;
    }

    /**
     * Store detail (status)
     */
    public void setAvailable(boolean available){
        this.available = available;
    }

    /**
     * Provide detail (status)
     */
    public void getAvailable(){
        return available;
    }



}
