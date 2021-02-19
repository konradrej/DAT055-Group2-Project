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
    public void setMonth(String s){
        this.month = s;
    }

    public void setDay(String d){
        this.day = d;
    }

    public void setTime(String t){
        this.time = t;
    }
    public String toString(){
        return this.day + " " + this.month + " " + this.time ;
    }
}
