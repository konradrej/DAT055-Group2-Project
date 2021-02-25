import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormatSymbols;
import java.util.ArrayList;

public class CinemaAdmin {
    private final JFrame f = new JFrame();
    private final Container pane = f.getContentPane();
    private int page = 1;
    private final CardLayout cl = new CardLayout();
    JPanel cardPanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    private Movie selectedMovie;
    private Theater selectedTheater;
    private CinemaDate date = new CinemaDate("Dec", "31", "23:59");

    public CinemaAdmin()
    {
        f.setTitle("Admin");
        f.setSize(300, 300);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cardPanel.setLayout(cl);
        f.setResizable(false);

        ArrayList<String> movieArr = new ArrayList<>();
        ArrayList<String> theaterArr = new ArrayList<>();
        ArrayList<String> dayArr = new ArrayList<>();

        for(Movie m : CinemaBookingSystem.getInstance().getMovieCollection().getAllMovies()){
            movieArr.add(m.getTitle());
        }

        for(Theater t : CinemaBookingSystem.getInstance().getCinema().getTheaterCollection()){
            theaterArr.add(Integer.toString(t.getTheaterNumber()));
        }

        for(int i = 1; i <= 31 ; i++){
            dayArr.add(Integer.toString(i));
        }

        JList<Object> jlm = new JList<>(movieArr.toArray());
        JScrollPane panelMovies = new JScrollPane(jlm);
        panelMovies.setColumnHeaderView(new JLabel("Movie:"));

        JList<Object> jlt = new JList<>((theaterArr.toArray()));
        JScrollPane panelTheaters = new JScrollPane(jlt);
        panelTheaters.setColumnHeaderView(new JLabel("Theater:"));

        JList<String> jlmon = new JList<>(new DateFormatSymbols().getMonths());
        JScrollPane panelMonth = new JScrollPane(jlmon);
        panelMonth.setColumnHeaderView(new JLabel("Month:"));

        JList jlday = new JList(dayArr.toArray());
        JScrollPane panelDay = new JScrollPane(jlday);
        panelDay.setColumnHeaderView(new JLabel("Day:"));

        JList<String> jltime = new JList<>(new String [] {"17:00", "19:00", "21:00"});
        JScrollPane panelTime = new JScrollPane(jltime);
        panelTime.setColumnHeaderView(new JLabel("Time:"));

        MouseListener m1 = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    for(Movie m: CinemaBookingSystem.getInstance().getMovieCollection().getAllMovies()){
                        if(m.getTitle().equals(jlm.getSelectedValue())){
                            selectedMovie = m;
                            break;
                        }
                    }
                }
            }
        };

        MouseListener m2 = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    for(Theater t : CinemaBookingSystem.getInstance().getCinema().getTheaterCollection()){
                        if(jlt.getSelectedValue().equals(Integer.toString(t.getTheaterNumber()))){
                            selectedTheater = t;
                            break;
                        }
                    }
                }
            }
        };

        MouseListener m3 = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String s = jlmon.getSelectedValue();
                    for(Show show : CinemaBookingSystem.getInstance().getShowCollection().getAllShows()){
                        System.out.println(show.getShowDateAndTime().toString());
                    }
                    date = date.setMonth(s);
                }
            }
        };

        MouseListener m4 = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String s = (String)jlday.getSelectedValue();
                    date  = date.setDay(s);
                }
            }
        };

        MouseListener m5 = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String s = jltime.getSelectedValue();
                    date = date.setTime(s);
                }
            }
        };

        jlm.addMouseListener(m1);
        jlt.addMouseListener(m2);
        jlmon.addMouseListener(m3);
        jlday.addMouseListener(m4);
        jltime.addMouseListener(m5);

        cardPanel.add(panelMovies, "1");
        cardPanel.add(panelTheaters, "2");
        cardPanel.add(panelMonth, "3");
        cardPanel.add(panelDay, "4");
        cardPanel.add(panelTime, "5");
        JButton prev = new JButton("Previous");
        JButton next = new JButton("Next");
        JButton addShow = new JButton("Add Show");

        prev.setEnabled(false);

        buttonPanel.add(prev);
        buttonPanel.add(next);
        buttonPanel.add(addShow);

        next.addActionListener((ActionEvent e) -> nextPage());
        prev.addActionListener((ActionEvent e) ->  prevPage());
        addShow.addActionListener((ActionEvent e)->  addShowToCollection());
        pane.add(cardPanel, BorderLayout.NORTH);
        pane.add(buttonPanel, BorderLayout.SOUTH);
        f.setVisible(true);
    }

    public void nextPage(){
        JButton next = (JButton) buttonPanel.getComponent(1);
        JButton prev = (JButton) buttonPanel.getComponent(0);
        if (page < 6) {
            cl.show(cardPanel, "" + (++page));
            prev.setEnabled(true);
        }
        else{
            next.setEnabled(false);
        }
    }

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

    public void addShowToCollection(){
        // TODO Theater status needs to be checked with date/time
        if(selectedMovie != null && selectedTheater != null ) {
            boolean addShow = true;
            for(Show s2 : CinemaBookingSystem.getInstance().getShowCollection().getAllShows()){
                if(s2.getTheater().equals(selectedTheater) && s2.getShowDateAndTime().equals(date)){
                    addShow = false;
                    JOptionPane.showMessageDialog(null, "Theater " + selectedTheater.getTheaterNumber() + " is occupied at " + date.toString(),
                            "Not added", JOptionPane.ERROR_MESSAGE);
                    break;
                }
            }
            if(addShow) {
                Show s = new Show(selectedMovie, date, CinemaBookingSystem.getInstance().getCinema(), selectedTheater, true);
                JOptionPane.showMessageDialog(null, s.toString(), "Show added", JOptionPane.INFORMATION_MESSAGE);
                CinemaBookingSystem.getInstance().getShowCollection().addShow(s);
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Choose movie, theater, date", "Not added", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args)
    {
        new Thread(SocketServerCommunication.getInstance()).start();
        CinemaBookingSystem.getInstance().readAllCollections();
        CinemaBookingSystem.getInstance().getMovieCollection().scanNewMovies();
        new CinemaAdmin();

        Runtime.getRuntime().addShutdownHook(new Thread(() ->
                CinemaBookingSystem.getInstance().updateAllCollections()));

    }
} 