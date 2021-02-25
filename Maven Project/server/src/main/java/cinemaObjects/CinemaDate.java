package cinemaObjects;

import java.io.Serializable;

public class CinemaDate implements Serializable {
    private String month;
    private String day;
    private String time;

    /**
     * Empty constructor for initializing the CinemaDate instance
     */
    public CinemaDate (){
    }

    /**
     * Constructor for initializing the CinemaDate instance
     *
     * @param m is the month
     * @param d is the day
     * @param t is the time
     */
    public CinemaDate(String m, String d, String t){
        this.month = m;
        this.day = d;
        this.time = t;
    }

    /**
     * Set method for month
     *
     * @param m the month which going to be set
     * @return a CinemaDate with s as month
     */

    public CinemaDate setMonth(String m){
        return new CinemaDate(m, this.day, this.time);
    }

    /**
     * Set method for day
     *
     * @param d the day which going to be set
     * @return a CinemaDate with d as day
     */

    public CinemaDate setDay(String d){
        return new CinemaDate(this.month, d, this.time);
    }

    /**
     * Set method for time
     *
     * @param t the time which is going to be set
     * @return a CinemaDate with t as time
     */

    public CinemaDate setTime(String t){
        return new CinemaDate(this.month, this.day, t);
    }

    /**
     * Get method to get the CinemaDate month
     * @return a string of the month
     */

    public String getMonth(){
        return month;
    }

    /**
     * Get method to get the CinemaDate day
     * @return a string of the day
     */

    public String getDay(){
        return this.day;
    }

    /**
     * Get method to get the CinemaDate time
     * @return a string of the time
     */

    public String getTime(){
        return this.time;
    }

    /**
     * A method that get the whole date
     * @return a string of the whole date
     */

    public String toString(){
        return this.day + " " + this.month + " " + this.time ;
    }

    /**
     * A method that indicates if the month is february
     * @return true if february
     */

    public boolean twentyeightDays(){
        return this.month.equals("February");
    }

    /**
     * A method that indicates if the month "contains" thirty one days
     * @return true if it contains thirty one days
     */
    public boolean thirtyoneDays(){
        String[] months = new String []{"January", "March", "May", "July", "August", "October", "December"};
        for(String m : months){
            if(m.equals(this.month)){
                return true;
            }
        }
        return false;
    }
}
