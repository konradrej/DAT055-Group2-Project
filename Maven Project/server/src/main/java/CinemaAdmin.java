import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import cinemaObjects.*;

public class CinemaAdmin {
    private int page;
    private final CardLayout cl;
    private final JPanel cardPanel;
    private final JPanel buttonPanel;
    private Movie selectedMovie;
    private Theater selectedTheater;
    private CinemaDate date;
    private JScrollPane panelDay;
    private JList<String> jlDay;


    /**
     * Constructor for initializing the CinemaAdmin with GUI
     */

    public CinemaAdmin()
    {
        cl = new CardLayout();
        cardPanel = new JPanel();
        buttonPanel = new JPanel();
        JFrame frame = new JFrame();
        frame.setTitle("Admin");
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cardPanel.setLayout(cl);
        frame.setResizable(false);

        page = 1;
        date = new CinemaDate();

        createListsWithListeners();
        createButtonsWithListeners();

        Container pane = frame.getContentPane();
        pane.add(cardPanel, BorderLayout.NORTH);
        pane.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    /**
     * Method for initializing every lists for Movie, Theater, Month, Day, Time with MouseListeners
     */

    public void createListsWithListeners(){
        jlDay = new JList<>();
        ArrayList<String> movieArr = new ArrayList<>();
        ArrayList<String> theaterArr = new ArrayList<>();



        for(Movie m : CinemaBookingSystem.getInstance().getMovieCollection().getAllMovies()){
            movieArr.add(m.getTitle());
        }

        for(Theater t : CinemaBookingSystem.getInstance().getCinema().getTheaterCollection()){
            theaterArr.add(Integer.toString(t.getTheaterNumber()));
        }


        JList<Object> jlMovie = new JList<>(movieArr.toArray());
        JScrollPane panelMovies = new JScrollPane(jlMovie);
        panelMovies.setColumnHeaderView(new JLabel("Movie:"));

        JList<Object> jlTheater = new JList<>((theaterArr.toArray()));
        JScrollPane panelTheaters = new JScrollPane(jlTheater);
        panelTheaters.setColumnHeaderView(new JLabel("Theater:"));

        JList<String> jlMonth = new JList<>(new DateFormatSymbols().getMonths());
        JScrollPane panelMonth = new JScrollPane(jlMonth);
        panelMonth.setColumnHeaderView(new JLabel("Month:"));

        panelDay = new JScrollPane(jlDay);
        panelDay.setColumnHeaderView(new JLabel("Day:"));

        JList<String> jlTime = new JList<>(new String [] {"17:00", "19:00", "21:00"});
        JScrollPane panelTime = new JScrollPane(jlTime);
        panelTime.setColumnHeaderView(new JLabel("Time:"));

        MouseListener movieListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    for(Movie m: CinemaBookingSystem.getInstance().getMovieCollection().getAllMovies()){
                        if(m.getTitle().equals(jlMovie.getSelectedValue())){
                            selectedMovie = m;
                            break;
                        }
                    }
                }
            }
        };

        MouseListener theaterListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    for(Theater t : CinemaBookingSystem.getInstance().getCinema().getTheaterCollection()){
                        if(jlTheater.getSelectedValue().equals(Integer.toString(t.getTheaterNumber()))){
                            selectedTheater = t;
                            break;
                        }
                    }
                }
            }
        };


        MouseListener dayListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    String s = jlDay.getSelectedValue();
                    date  = date.setDay(s);
                }
            }
        };

        MouseListener monthListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    String s = jlMonth.getSelectedValue();
                    date = date.setMonth(s);
                    if(date.thirtyoneDays()) {
                        jlDay = new JList<>(setDaysInList(31));
                    }
                    else if (date.twentyeightDays()){
                        jlDay = new JList<>(setDaysInList(28));
                    }
                    else{
                        jlDay = new JList<>(setDaysInList(30));
                    }
                    panelDay = new JScrollPane(jlDay);
                    cardPanel.add(panelDay, "4");
                    panelDay.setColumnHeaderView(new JLabel("Day: "));
                    jlDay.addMouseListener(dayListener);
                }
            }
        };

        MouseListener timeListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    String s = jlTime.getSelectedValue();
                    date = date.setTime(s);
                }
            }
        };
        jlMovie.addMouseListener(movieListener);
        jlTheater.addMouseListener(theaterListener);
        jlMonth.addMouseListener(monthListener);
        jlDay.addMouseListener(dayListener);
        jlTime.addMouseListener(timeListener);

        cardPanel.add(panelMovies, "1");
        cardPanel.add(panelTheaters, "2");
        cardPanel.add(panelMonth, "3");
        cardPanel.add(panelDay, "4");
        cardPanel.add(panelTime, "5");
    }

    /**
     * Method for creating buttons with ActionListeners
     */

    public void createButtonsWithListeners(){
        JButton prev = new JButton("Previous");
        JButton next = new JButton("Next");
        JButton addShow = new JButton("Add Show");
        prev.setEnabled(false);

        next.addActionListener((ActionEvent e) -> nextPage());
        prev.addActionListener((ActionEvent e) ->  prevPage());
        addShow.addActionListener((ActionEvent e)->  addShowToCollection());

        buttonPanel.add(prev);
        buttonPanel.add(next);
        buttonPanel.add(addShow);
    }

    /**
     * Method to return an amount of days ( 1 to x)
     *
     * @param days amount of days
     * @return a string array of days
     */

    public String [] setDaysInList(int days){
        String [] dayInArr = new String[days];
        for(int i = 1; i <= days ; i++){
            dayInArr[i-1] = Integer.toString(i);
        }
        return dayInArr;
    }

    /**
     * Method to switch to next page of the panel
     */

    public void nextPage(){
        JButton next = (JButton) buttonPanel.getComponent(1);
        JButton prev = (JButton) buttonPanel.getComponent(0);
        if (page < 5) {
            cl.show(cardPanel, "" + (++page));
            prev.setEnabled(true);
        }
        else{
            next.setEnabled(false);
        }
    }

    /**
     * Method to switch to previous page of the panel
     */

    public void prevPage(){
        JButton next = (JButton) buttonPanel.getComponent(1);
        JButton prev = (JButton) buttonPanel.getComponent(0);
        if (page > 1) {
            cl.show(cardPanel, "" + (--page));
            next.setEnabled(true);
        }
        else{
            prev.setEnabled(false);
        }
    }

    /**
     * Method that adds a show if Movie, Theater and date are valid
     */

    public void addShowToCollection(){

        if(selectedMovie != null && selectedTheater != null &&
                date.getMonth() != null && date.getTime() != null && date.getDay() != null) {
            boolean addShow = true;
            for(Show s2 : CinemaBookingSystem.getInstance().getShowCollection().getAllShows()){
                if(s2.getTheater().getTheaterNumber() == selectedTheater.getTheaterNumber() &&
                        s2.getShowDateAndTime().equals(date)){
                    addShow = false;
                    JOptionPane.showMessageDialog(null, "Theater " + selectedTheater.getTheaterNumber() +
                                    " is occupied at " + date.toString(),
                            "Not added", JOptionPane.ERROR_MESSAGE);
                    break;
                }
            }
            if(addShow) {
                Theater t = selectedTheater.cloneTheater();
                Show s = new Show(selectedMovie, date, CinemaBookingSystem.getInstance().getCinema(),t);
                JOptionPane.showMessageDialog(null, s.toString(), "Show added", JOptionPane.INFORMATION_MESSAGE);
                CinemaBookingSystem.getInstance().getShowCollection().addShow(s);
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Choose movie, theater, date", "Not added", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) throws IOException {
        new Thread(SocketServerCommunication.getInstance()).start();
        CinemaBookingSystem.getInstance().readAllCollections();
        CinemaBookingSystem.getInstance().getMovieCollection().scanNewMovies();
        CinemaBookingSystem.getInstance().createCustomer("Konrad", "000000000", "99");
        new CinemaAdmin();

        Runtime.getRuntime().addShutdownHook(new Thread(() ->
                CinemaBookingSystem.getInstance().serializeAllCollection()));

    }
}