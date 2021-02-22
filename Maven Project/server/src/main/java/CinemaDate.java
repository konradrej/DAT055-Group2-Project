import java.io.Serializable;

public class CinemaDate implements Serializable {
    private String month;
    private String day;
    private String time;

    public CinemaDate(String m, String d, String t){
        this.month = m;
        this.day = d;
        this.time = t;
    }
    public CinemaDate setMonth(String s){
        return new CinemaDate(s, this.day, this.time);
    }

    public CinemaDate setDay(String d){
        return new CinemaDate(this.month, d, this.time);
    }

    public CinemaDate setTime(String t){
        return new CinemaDate(this.month, this.day, t);
    }

    public String getMonth(){
        return month;
    }

    public String getDay(){
        return this.day;
    }

    public String getTime(){
        return this.time;
    }

    public String toString(){
        return this.day + " " + this.month + " " + this.time ;
    }
}
