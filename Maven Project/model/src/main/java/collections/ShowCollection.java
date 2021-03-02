package collections;

import cinemaObjects.*;

import java.awt.*;
import java.io.*;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Anthon Lenander, Erik Kieu, Phong Nguyen
 * @version version 0.0.0
 */
public class ShowCollection extends AbstractCollection {

    private static final long serialVersionUID = 368284506033169560L;
    private final List<Show> allShows;
    private final String filename;

    /**
     * Constructor for initializing the ShowCollection instance
     *
     * @param filename Filename of the object when serializing
     */

    public ShowCollection(String filename) {
        this.filename = filename;
        allShows = new ArrayList<>();
    }

    /**
     * Get a collection of selected shows given movie
     *
     * @param m - the movie being searched
     * @return a collection of shows
     */

    public ShowCollection getShowsGivenMovie(Movie m) {
        ShowCollection s = new ShowCollection("getShowsGivenMovie");
        for (Show s2 : getAllShows()) {
            if (m.getTitle().equals(s2.getMovie().getTitle())) {
                s.addShow(s2);
            }
        }
        return s;
    }

    /**
     * Get a collection of selected shows given start date
     * and optionally end date.
     *
     * @param startDate first allowed date
     * @param endDate   last allowed date
     * @return a collection of shows
     */

    public ShowCollection getShowsGivenDateRange(CinemaDate startDate, CinemaDate endDate) {
        ShowCollection s = new ShowCollection("getShowsGivenDateRange");
        for (Show s2 : getAllShows()) {
            CinemaDate showDate = s2.getShowDateAndTime();
            boolean addShow = true;

            int showDateMonthNumber = 5;
            List<String> months = new ArrayList<>();
            for (String month : new DateFormatSymbols().getMonths()) {
                months.add(month.toLowerCase());
            }

            for(int i = 1; i <= months.size(); i++){

                if(months.get(i-1).equals(showDate.getMonth().toLowerCase())){
                    showDateMonthNumber = i;
                    break;
                }
            }

            if (startDate != null) {
                if (Integer.parseInt(startDate.getMonth()) > showDateMonthNumber) {
                    addShow = false;
                } else if (Integer.parseInt(startDate.getMonth()) == showDateMonthNumber) {
                    if (Integer.parseInt(startDate.getDay()) > Integer.parseInt(showDate.getDay())) {
                        addShow = false;
                    }
                }
            }

            if (endDate != null) {
                if (Integer.parseInt(endDate.getMonth()) < showDateMonthNumber) {
                    addShow = false;
                } else if (Integer.parseInt(endDate.getMonth()) == showDateMonthNumber) {
                    if (Integer.parseInt(endDate.getDay()) < Integer.parseInt(showDate.getDay())) {
                        addShow = false;
                    }
                }
            }

            if (addShow) {
                s.addShow(s2);
            }
        }
        return s;
    }

    public List<Seat> getAllSeats(Show show) {
        List<Seat> allSeats = new ArrayList<>();

        for (Show s : this.allShows) {
            if (s.equals(show)) {
                allSeats = s.getAllSeats();
            }
        }

        return allSeats;
    }

    public List<Seat> getAllAvailableSeats(Show s) {
        for (Show s2 : this.allShows) {
            if (s2.equals(s)) {
                return s2.getAllAvailableSeats();
            }
        }
        System.out.println("Show not found / no collection of seats available");
        return null;
    }

    public List<Seat> getAdjacentSeats(Show s, int numOfSeats) {
        for (Show s2 : this.allShows) {
            if (s2.equals(s)) {
                return s2.getAdjacentAvailableSeats(numOfSeats);
            }
        }
        System.out.println("Show not found / no collection of seats available");
        return null;
    }


    /**
     * Adds a show given movie, date and time, cinema and theater:
     *
     * @param s - The show that is added
     */

    public void addShow(Show s) {
        this.allShows.add(s);
    }

    /**
     * Removes a show given show
     *
     * @param s the show that being removed
     */

    public void removeShow(Show s) {
        for(Show s2: this.allShows) {
            if(s2.equals(s)) {
                this.allShows.remove(s2);
                return;
            }
        }
        System.out.println("Show not found");
    }

    /**
     * Update a show given movie, date and time, cinema and theater
     *
     * @param s   - The show being updated
     * @param m   - The updated movie of the show
     * @param dat - The updated day and time of the movie
     * @param c   - The updated cinema of the show
     * @param t   - The updated theater of the show
     */

    public void updateShow(Show s, Movie m, CinemaDate dat, Cinema c, Theater t) {
        for (Show s2 : allShows) {
            if (s.getUniqueID().equals(s2.getUniqueID())) {
                this.removeShow(s2);
                this.addShow(new Show(m, dat, c, t));
                break;
            }
        }
    }

    public Show getShowByUID(String uid) {
        for (Show s : this.getAllShows()) {
            if (uid.equals(s.getUniqueID())) {
                return s;
            }
        }
        //TODO något annat
        return null;
    }

    /**
     * Get method to get all shows from the objects collection
     *
     * @return - a collection of shows
     */

    public List<Show> getAllShows() {
        return this.allShows;
    }

    /**
     * Get method for the filename when serializing
     *
     * @return a string of the filename
     */

    public String getFilename() {
        return this.filename;
    }

    /**
     * Read serialized file
     *
     * @return ShowCollection of the read file
     * @throws NullPointerException returns an empty ShowCollection
     */

    public ShowCollection readCollection() {
        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(this.filename))) {

            ShowCollection readThis = (ShowCollection) stream.readObject();
            System.out.println("File: " + this.filename + " has been read");
            return readThis;
        } catch (ClassCastException | ClassNotFoundException | FileNotFoundException | NullPointerException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return new ShowCollection(this.filename);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
