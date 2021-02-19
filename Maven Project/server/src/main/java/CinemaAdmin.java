import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

// class extends JFrame
public class CinemaAdmin {
    private final JFrame f = new JFrame();
    private final Container pane = f.getContentPane();
    private int page = 1;
    private final CardLayout cl = new CardLayout();
    private final JPanel cardPanel = new JPanel();
    private final JPanel buttonPanel = new JPanel();
    private static  Movie movie;
    private static Theater theater;
    private static  CinemaDate date = new CinemaDate("Dec", "21", "23:59");

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

        JList jlm = new JList(movieArr.toArray());
        JScrollPane panelMovies = new JScrollPane(jlm);
        panelMovies.setColumnHeaderView(new JLabel("Movie:"));

        JList jlt = new JList((theaterArr.toArray()));
        JScrollPane panelTheaters = new JScrollPane(jlt);
        panelTheaters.setColumnHeaderView(new JLabel("Theater:"));

        JList jlmon = new JList(new DateFormatSymbols().getMonths());
        JScrollPane panelMonth = new JScrollPane(jlmon);
        panelMonth.setColumnHeaderView(new JLabel("Month:"));

        JList jlday = new JList(dayArr.toArray());
        JScrollPane panelDay = new JScrollPane(jlday);
        panelDay.setColumnHeaderView(new JLabel("Day:"));

        JList jltime = new JList(new String [] {"17:00", "19:00", "21:00"});
        JScrollPane panelTime = new JScrollPane(jltime);
        panelTime.setColumnHeaderView(new JLabel("Time:"));

        MouseListener m1 = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    System.out.println(jlm.getSelectedValue());
                    for(Movie m: CinemaBookingSystem.getInstance().getMovieCollection().getAllMovies()){
                        if(m.getTitle().equals(jlm.getSelectedValue())){
                            CinemaAdmin.movie = m;
                            break;
                        }
                    }
                }
            }
        };
        jlm.addMouseListener(m1);

        MouseListener m2 = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    for(Theater t : CinemaBookingSystem.getInstance().getCinema().getTheaterCollection()){
                        if(jlt.getSelectedValue().equals(Integer.toString(t.getTheaterNumber()))){
                            CinemaAdmin.theater = t;
                            break;
                        }
                    }
                }
            }
        };
        jlt.addMouseListener(m2);

        MouseListener m3 = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String s = (String)jlmon.getSelectedValue();
                    date.setMonth(s);
                }
            }
        };
        jlmon.addMouseListener(m3);

        MouseListener m4 = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String s = (String)jlday.getSelectedValue();
                    date.setDay(s);
                }
            }
        };
        jlday.addMouseListener(m4);

        MouseListener m5 = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String s = (String) jltime.getSelectedValue();
                    date.setTime(s);
                }
            }
        };
        jltime.addMouseListener(m5);

        cardPanel.add(panelMovies, "1");
        cardPanel.add(panelTheaters, "2");
        cardPanel.add(panelMonth, "3");
        cardPanel.add(panelDay, "4");
        cardPanel.add(panelTime, "5");
        JButton prev = new JButton("Previous");
        JButton next = new JButton("Next");
        JButton addShow = new JButton("Add Show");

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
        if (page < 6) {
            cl.show(cardPanel, "" + (++page));
        }
    }

    public void prevPage(){
        if (page > 1) {
            cl.show(cardPanel, "" + (--page));
        }
    }

    public void addShowToCollection(){
        if(movie != null && theater != null && theater.getStatus()) {
            Show s = new Show(movie, date, CinemaBookingSystem.getInstance().getCinema(), theater, true);
            CinemaBookingSystem.getInstance().getShowCollection().addShow(s);
            theater.setStatus(false);
            System.out.println(s.toString());
        }
        else if (!theater.getStatus()){
            System.out.println("Theater" + theater.getTheaterNumber() + " occupied");
        }
        else{
            System.out.println("choose movie, theater, date");
        }

    }

    public static void main(String[] args)
    {
        new Thread(SocketServerCommunication.getInstance()).start();
        CinemaBookingSystem.getInstance().getMovieCollection().scanNewMovies();

        new CinemaAdmin();

    }
} 